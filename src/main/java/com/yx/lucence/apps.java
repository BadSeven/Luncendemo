package com.yx.lucence;


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
import org.apache.lucene.util.Version;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class apps {
    /*
    *
    * */

    public void createIndexDB() throws IOException {
        //创建文件对象
        Article article = new Article(1,"就是一个测试的实体类","love my ");
        //创建document对象
        Document document = new Document();
        //把Articled对象的属性值绑定到document对象中
        /**
         * 1  id名称
         * 2  对应的实体类
         * 3  属性值是不是存入词汇表
         * 4  分词 提倡非id都进行拆分 目前一个汉字一个分词拆分
         * */
        document.add(new Field("id",article.getId().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("title",article.getTitle().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content",article.getContent().toString(), Field.Store.YES, Field.Index.ANALYZED));
        //把document放入 索引库

        /**
         * 1 目录 相当于是数据库存在的位置
         * 2 算法策略 把文本拆分
         * 3 最多把文本查分出多少个词汇 LIMITED 最多查分成多少个字段  只取1w个 如果不足一万 以实际为准
         * */


        Version version = Version.LUCENE_30;
        Analyzer analyzer  = new StandardAnalyzer(version);
        IndexWriter.MaxFieldLength maxFieldLength = IndexWriter.MaxFieldLength.LIMITED;
        Directory directory = FSDirectory.open(new File("F:/db"));
        IndexWriter indexWriter = new IndexWriter(directory,analyzer,maxFieldLength);
        //把document写入lucence库
        indexWriter.addDocument(document);
        indexWriter.close();
    }
    public void findindexdbkey(String key) throws IOException, ParseException {
        List<Article> list = new ArrayList<Article>();
        Version version = Version.LUCENE_30;
        Directory directory = FSDirectory.open(new File("F:/db"));
        //创建搜索接口指定在那个位置
        IndexSearcher indexSearcher =   new IndexSearcher(directory);
        //分词器
        Analyzer analyzer = new StandardAnalyzer(version);
        /**
         * 1 使用分词器的版本建议使用该jar包中的最高版本
         * 2 针对对象中的那个属性进行搜索
         * 3 分词器
         * */
        QueryParser queryParser = new QueryParser(version,"content",analyzer);//查询策略
        Query query =queryParser.parse(key);
        /**
        *根据关键之去索引库的词汇表进行搜索
        * 1 查询解析器
        * 2 如果结果较多 我们只取max个
        * */
         int Max=10;
       TopDocs topDocs =  indexSearcher.search(query,Max);
       for(int i=0;i<topDocs.scoreDocs.length;i++){
           //分数 关注度得分
           ScoreDoc scoreDoc = topDocs.scoreDocs[i];
           //取出标号
          int no =  scoreDoc.doc;
          //去原始document查询数据
          Document document = indexSearcher.doc(no);
          Article article = new Article( Integer.parseInt(document.get("id")),
           document.get("title"),
           document.get("content"));
          list.add(article);
          System.out.println(article.toString());
       }
        for(Article a:list){
            System.out.println( a.toString());
        }
    }
    public static void main(String[] args) throws IOException, ParseException {
        apps app = new apps();
        app.createIndexDB();
        app.findindexdbkey("love");
    }

}
