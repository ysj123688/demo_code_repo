package mongodb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;  
  
/**
 * 
 * @ClassName: MongoDBUtil 
 * @Description: 
 * @author jerome_s@qq.com
 * @date 2015年11月3日 下午5:07:18 
 *	<dependency>
		<groupId>org.mongodb</groupId>
		<artifactId>mongo-java-driver</artifactId>
		<version>3.1.0</version>
	</dependency>
 */
@SuppressWarnings("deprecation")
public class MongoDBUtil {  
    static MongoClient client;  
    static DB db;  
    static DBCollection collection;  
  
    static {  
		System.out.println("连接服务器测试.................");  
//		ServerAddress serverAddress = new ServerAddress("vrouter.lz-oa.com", 27017);  
		ServerAddress serverAddress = new ServerAddress();  // 默认localhost 27017
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();  
		seeds.add(serverAddress);  
//		MongoCredential credentials = MongoCredential.createMongoCRCredential("wlw_user", "wlw_test",  
//		        "FxxT".toCharArray());  
		
//		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();  
//		credentialsList.add(credentials);  
//		client = new MongoClient(seeds, credentialsList);  
		client = new MongoClient(seeds);  
		// (List<ServerAddress> seeds, List<MongoCredential//  
		db = client.getDB("wlw_test");  // 获取数据库
		// db.authenticate("testAdmin", "123".toCharArray());  
		collection = db.getCollection("wlw_test");  // 获取集合
    }   
  
    public static void add(DBObject dbObject) {  
        collection.insert(dbObject);  
    }  
  
    public static void del(DBObject dbObject) {  
        collection.remove(dbObject);  
    }  
  
    public static void update(DBObject dbObject1, DBObject dbObject2) {  
        collection.update(dbObject1, dbObject2);  
    }  
  
    public static DBObject queryOneByKey(DBObject dbObject, DBObject keys) {  
        DBCursor dbCursor = collection.find(dbObject, keys);  
        if (dbCursor.hasNext()) {  
            return dbCursor.next();  
        }  
        return null;  
    }  
  
    public static java.util.List<DBObject> query(DBObject dbObject) {  
        DBCursor dbCursor = collection.find(dbObject);  
        List<DBObject> ret = new ArrayList<DBObject>();  
        while (dbCursor.hasNext()) {  
            DBObject dbObject2 = dbCursor.next();  
            ret.add(dbObject2);  
  
        }  
        return ret;  
    }  
  
      
      
    public static void main(String[] args) {  
        BasicDBObject dbObject = new BasicDBObject();  
//        dbObject.put("num", new BasicDBObject("$gt", 0));  
        List<DBObject> ret = MongoDBUtil.query(dbObject);  
        for (Iterator<DBObject> it = ret.iterator(); it.hasNext();) {  
            System.out.println(it.next());  
        }  
        System.out.println("查询结束");
    }  
  
}  