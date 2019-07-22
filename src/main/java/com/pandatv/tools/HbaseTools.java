package com.pandatv.tools;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by likaiqing on 2017/10/23.
 */
public class HbaseTools {
    private static final Logger logger = LoggerFactory.getLogger(HbaseTools.class);

    public static void main(String[] args) {
//        boolean compressedTable = createCompressedTable("panda_personas.panda_guid_last_msg", "last_30_days");
//        dropTable("guid_last_msg");
        System.setProperty("zookeeper.sasl.client", "false");
//        addFamily("panda_personas.client_guid_view_msg", true, "last_msg");
        createTable("panda_personas.client_guid_view_msg", true, "last_msg");
    }

    public static boolean createCompressedTable(String tableName, String... cfs) {
        return createTable(tableName, true, cfs);
    }

    public static boolean createUncompressedTable(String tableName, String... cfs) {
        return createTable(tableName, false, cfs);
    }

    private static boolean createTable(String tableName, boolean compress, String... cfs) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection();
            Admin admin = connection.getAdmin();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                if (admin.isTableEnabled(TableName.valueOf(tableName))) {
                    logger.warn("table:" + tableName + " exists in hbase and enabled");
                } else {
                    logger.warn("table:" + tableName + " exists in hbase and unenabled");
                }
                return false;
            } else {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
                for (String cf : cfs) {
                    if (compress) {
                        hTableDescriptor.addFamily(new HColumnDescriptor(cf).setCompressionType(Compression.Algorithm.SNAPPY));
                    } else {
                        hTableDescriptor.addFamily(new HColumnDescriptor(cf).setCompressionType(Compression.Algorithm.NONE));
                    }
                }
                byte[][] splitKeys = getSplitKeys();
//                admin.createTable(hTableDescriptor);
                admin.createTable(hTableDescriptor, splitKeys);
                logger.info("create table:" + tableName);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed() || connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static byte[][] getSplitKeys() {
        String[] keys = new String[]{"10|", "20|", "30|", "40|", "50|",
                "60|", "70|", "80|", "90|"};
        byte[][] splitKeys = new byte[keys.length][];
        TreeSet<byte[]> rows = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);//升序排序
        for (int i = 0; i < keys.length; i++) {
            rows.add(Bytes.toBytes(keys[i]));
        }
        Iterator<byte[]> rowKeyIter = rows.iterator();
        int i = 0;
        while (rowKeyIter.hasNext()) {
            byte[] tempRow = rowKeyIter.next();
            rowKeyIter.remove();
            splitKeys[i] = tempRow;
            i++;
        }
        return splitKeys;
    }

    public static boolean dropTable(String tableName) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection();
            Admin admin = connection.getAdmin();
            if (admin.tableExists(TableName.valueOf(tableName))) {
                if (admin.isTableEnabled(TableName.valueOf(tableName))) {
                    admin.disableTable(TableName.valueOf(tableName));
                }
                admin.deleteTable(TableName.valueOf(tableName));
            } else {
                logger.info("drop table error,table:" + tableName + " not exists¬");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed() || connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean addCompressFamily(String tableName, String... cfs) {
        return addFamily(tableName, true, cfs);
    }

    public static boolean addUncompressFamily(String tableName, String... cfs) {
        return addFamily(tableName, false, cfs);
    }

    private static boolean addFamily(String tableName, boolean compress, String... cfs) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection();
            Admin admin = connection.getAdmin();
            if (!admin.tableExists(TableName.valueOf(tableName))) {
                logger.warn("table:" + tableName + " not exists");
                return false;
            }
            HTableDescriptor tableDescriptor = admin.getTableDescriptor(TableName.valueOf(tableName));
            Set<String> cfNames = Arrays.stream(tableDescriptor.getColumnFamilies()).map(p -> p.getNameAsString()).collect(Collectors.toSet());
            for (String cf : cfs) {
                boolean contains = cfNames.contains(cf);
                if (contains) {
                    logger.warn("tbale:" + tableName + ",contains column family:" + cf);
                    continue;
                }
                if (compress) {
                    admin.addColumn(TableName.valueOf(tableName), new HColumnDescriptor(cf).setCompressionType(Compression.Algorithm.SNAPPY));
                } else {
                    admin.addColumn(TableName.valueOf(tableName), new HColumnDescriptor(cf).setCompressionType(Compression.Algorithm.NONE));
                }

            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed() || connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
