package com.pandatv.tools;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by likaiqing on 2017/4/26.
 */
public class HiveJDBCConnect {
    private final static Logger logger = LoggerFactory.getLogger(HiveJDBCConnect.class);
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    //    private static String url = "jdbc:hive2://10.110.19.9:10000/panda_realtime";
//    private static String user = "bigdata";
//    private static String password = "cHjhAFeSDpBMrkxyOPzp";
//    private static String url = "jdbc:hive2://emr-header-1:2181,emr-header-2:2181,emr-worker-1:2181/panda_realtime;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";
    private static String url = "jdbc:hive2://10.131.6.3:2181,10.131.6.16:2181,10.131.6.12:2181/panda_realtime;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";
    private static String sql = "";
    private static ResultSet res;
    private static int tryTimes = 0;
    private static Connection conn = null;
    private static Statement stmt = null;
    private ResultSet result = null;
    private static String path = "/bigdata/hive/panda_realtime/";
    private static Configuration conf = null;

    static {
        conf = new Configuration();
        conf.addResource(HiveJDBCConnect.class.getClassLoader().getResourceAsStream("hdfs-site.xml"));
        conf.addResource(HiveJDBCConnect.class.getClassLoader().getResourceAsStream("core-site.xml"));
        conf.addResource(HiveJDBCConnect.class.getClassLoader().getResourceAsStream("mapred-site.xml"));
        try {
            Properties properties = new Properties();
            properties.load(HiveJDBCConnect.class.getClassLoader().getResourceAsStream("hive2.properties"));
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("zookeeper.sasl.client", "false");
    }

    public static void main(String[] args) {
        HiveJDBCConnect hive = new HiveJDBCConnect();
        try {
            hive.getConn();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void write(String path, Collection<String> list) {
        path = (path.endsWith("/")) ? path : (path + "/");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        org.apache.hadoop.fs.FileSystem fs;
        try {
            fs = org.apache.hadoop.fs.FileSystem.get(conf);
            CompressionCodecFactory factory = new CompressionCodecFactory(conf);
            FSDataOutputStream hdfsOutStream = null;
            CompressionCodec codec = factory.getCodecByName("DEFLATE");
            Compressor compressor = CodecPool.getCompressor(codec, conf);
            CompressionOutputStream cmpOut = null;
            String filepath = path + System.currentTimeMillis() + "_" + Math.random() + ".deflate";
            if (fs.exists(new Path(filepath))) {
                hdfsOutStream = fs.append(new Path(filepath));
            } else {
                hdfsOutStream = fs.create(new Path(filepath));
            }

            cmpOut = codec.createOutputStream(hdfsOutStream, compressor);
            for (String subStr : list) {
                byte[] line = (subStr + "\n").getBytes("UTF-8");
                if (line == null)
                    continue;
                cmpOut.write(line);
            }
            cmpOut.finish();
            cmpOut.close();
            hdfsOutStream.close();
            fs.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet excutesql(String sql) {
        try {
            if (conn == null) {
                conn = getConn();
            }
            stmt = conn.createStatement();
//			new GetLogThread().start();
            result = stmt.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.warn(e.toString());
            if (e.toString().contains("return code 1") && tryTimes < 5) {
                tryTimes++;
                int waitingTime = tryTimes * 120000;
                logger.warn("maybe timesout; 30 second later retry " + tryTimes + "times;");
                try {
                    Thread.sleep(waitingTime);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return excutesql(sql);
            }
            logger.error("try 3 times, exit");
        }
        return result;
    }

    private Connection getConn() throws ClassNotFoundException, SQLException {
        Properties info = new Properties();
        Class.forName(driverName);
//        if (!info.containsKey("user")) {
//            info.put("user", user);
//        }
//        if (!info.containsKey("password")) {
//            info.put("password", password);
//            ;
//        }
        info.put("user", "root");
        /*
        密码在两个master需要配置/usr/local/service/hive/conf/beelinepasswd,并且密码是密文，md5加密
         */
        info.put("password", "pandaroot@bigdata");

        info.put("hiveconf:hive.tez.container.size", "4096");
        Connection conn = DriverManager.getConnection(url, info);
        return conn;
    }

}
