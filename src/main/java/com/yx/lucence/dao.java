package com.yx.lucence;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2018/1/16.
 */
public class dao {
    public int getallR(String key) throws Exception
    {
        //查询全部
        List<Article> list = new ArrayList<Article>();


        Version version = Version.LUCENE_30;
        //目录
        Directory directory = FSDirectory.open(new File("f:/db"));
        //输入流
        IndexSearcher indexSearcher =   new IndexSearcher(directory);
        //标准分词器
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
        int Max=1000;
        TopDocs topDocs =  indexSearcher.search(query,Max); //
        int count = topDocs.totalHits;//命中多少


        return 7;
    }

    /***
     * 分页
     * @param key
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<Article>  findall(String key,int start,int size) throws Exception{
        ArrayList list = new ArrayList();
        Version version = Version.LUCENE_30;
        //目录
        Directory directory = FSDirectory.open(new File("f:/db"));
        //输入流
        IndexSearcher indexSearcher =   new IndexSearcher(directory);
        //标准分词器
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
        int Max=100;
        TopDocs topDocs =  indexSearcher.search(query,Max); //
        int max = Math.min(size+start,topDocs.totalHits);
        for(int x=start;x<max;x++){
            ScoreDoc docs = topDocs.scoreDocs[x];
            int no = docs.doc;
            list.add(LuceneUtil.document2javabean(indexSearcher.doc(no),Article.class));
        }

        return list;
    }


}
