package com.yx.lucencedemo2;


import com.yx.lucence.LuceneUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2018/1/16.
 */
public class Optimize {

    public  void add() throws IOException {
        book b = new book(1,"seven","第一章节",500);

        Document document = new Document();
        Directory directory = FSDirectory.open(new File("f://mydb"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter indexWriter = new IndexWriter(directory,analyzer, IndexWriter.MaxFieldLength.LIMITED);
        document.add(new Field("id",b.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("author",b.getAuthor().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content",b.getContent().toString(), Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(document);
        indexWriter.close();

    }
    //优化合并
    public void type() throws IOException {
        book b = new book(1,"seven","第一章节",500);

        Document document = new Document();
        Directory directory = FSDirectory.open(new File("f://mydb"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
        IndexWriter indexWriter = new IndexWriter(directory,analyzer, IndexWriter.MaxFieldLength.LIMITED);
        document.add(new Field("id",b.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("author",b.getAuthor().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content",b.getContent().toString(), Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(document);
        indexWriter.optimize();//合并cfs文本的方式解决文本是数量
        //合并因子 自动合并文件没次100个文件就进行合并
        indexWriter.setMergeFactor(100);
        indexWriter.close();
    }
    public void getall(String key) throws IOException, ParseException {
        List<book> books = new ArrayList<book>();
        Directory directory = FSDirectory.open(new File("f:/mydb"));
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        Version version = Version.LUCENE_30;
        QueryParser queryParser = new QueryParser(version,"content",new StandardAnalyzer(version));
        Query query =queryParser.parse(key);
        TopDocs topDocs = indexSearcher.search(query,100);
        for(int x=0;x<topDocs.scoreDocs.length;x++){
            ScoreDoc scoreDoc = topDocs.scoreDocs[x];
            int no = scoreDoc.doc;
           Document document =  indexSearcher.doc(no);
           document.get("id");
        }



    }
    public void type4() throws IOException {
        Directory directory = FSDirectory.open(new File("f:mydb"));

        Directory rmdir = new RAMDirectory(directory); //硬盘索引库到内存索引库
        //true如果内存索引和硬盘索引库中有相同的对象时候先删除然后硬盘中的documenet对象在把内存索引库中写入
        IndexWriter fsindex = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),true,LuceneUtil.getMaxFieldLength());

        IndexWriter ramIndex = new IndexWriter(rmdir,LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());

        //写入到内存索引
        Document document = new Document();
        ramIndex.addDocument(document);


        //把内存索引库中的数据存入到磁盘索引库中
        fsindex.addIndexesNoOptimize(rmdir); //内存写到磁盘
        ramIndex.close();
        fsindex.close();

    }

}
