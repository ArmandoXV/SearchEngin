package first.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;  // Import the File class

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {
    String indexDir = "D:\\Lucene\\Index";
    String dataDir = path;
    static String uri = "C:\\Users\\Mhammd\\Desktop\\5th\\2\\Info ret\\Practical\\Lab1\\bbc_rss_feed.xml";
    static String path = "D:\\Lucene\\Files\\";

    Indexer indexer;
    Searcher searcher;

//   	public static void main(String[] args) {
//
//		ReadingRSSFeed(uri,path);
//
//   		LuceneTester tester;
//   		try {
//   			tester = new LuceneTester();
//   			tester.createIndex();
//   			tester.search("Iraq");
//   		} catch (Exception e) {
//   			e.printStackTrace();
//   		}
//   	}

    public static void ReadingRSSFeed(String uri, String writingPath) {
        RssFeedDocument rssFeedDocument = new RssFeedDocument();
        RssFeedParser rssFeedParser = new RssFeedParser();
        rssFeedParser.parse(uri);
        int i = 1;

        for (RssFeedDocument t : rssFeedParser.getDocuments()) {
            //Creating the files
            try {
                File myObj = new File(path + i + ".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //writing to the files
            try {
                FileWriter myWriter = new FileWriter(path + i + ".txt");
                myWriter.write(t.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            i++;
        }
    }

    public String createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();

        String s = (numIndexed + " File indexed, time taken: " + (endTime - startTime) + " ms");
        return s;
    }

    public String search(String searchQuery) throws Exception {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

		String s = "";
		s+= (hits.totalHits +
				" documents found. Time :" + (endTime - startTime) + "\n");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            s +=("File: " + doc.get(LuceneConstants.FILE_PATH) + "\n");
        }
		return s;

    }


}
