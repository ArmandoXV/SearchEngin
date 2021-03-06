package first.app;

import java.util.List;

public class Controller {
    static String indexDir = new String();
    static String dataDir = new String();
     public static String uri = new String();

    Indexer indexer;
    Searcher searcher;
    static LuceneTester tester;

    public Controller() {

    }

    public static String setIndexDir(String indexDir) {
        Controller.indexDir = indexDir;
        return "Set Index Dir to: " + indexDir;
    }

    public static String setDataDir(String dataDir) {
        Controller.dataDir = dataDir;
        return "Set Data Dir to: " + dataDir;
    }

    public static String setUri(String uri) {
        Controller.uri = uri;
        return "Set URIs To: " + uri;
    }

    public static void ReadAndWriteData(){

        tester.ReadingRSSFeed(uri,dataDir);

        try{
            tester = new LuceneTester();
            tester.createIndex();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String Search(String searchQuery){
        try {
            return tester.search(searchQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
