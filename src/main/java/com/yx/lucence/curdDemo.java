package com.yx.lucence;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2018/1/16.
 */
public class curdDemo {

    //添加一个
    public  void addIndex() throws Exception {
        //创建对象
        Article article1 = new Article(1,"我还爱你","真希望多年后的夜晚，我在厨房洗完餐具，你在客厅里挑选碟片，我拌好一盆蔬菜水果沙拉，你选好一部老旧电影，走到厨房把我抱到沙发上让我靠在你怀里而你懒散的靠在沙发背上，我们就看电影，说起话来没有话题，只是说出想说的零零总总，你说这样的日子很安稳，我说时间太快还想和你过上几百年。");
        //Article article2 = new Article(2,"大雁","有人喜欢，就有人不待见，平常不过何必执着，刻板印象不论好坏都好比顽石不碎，何况人无全优，自然也在情理之中不在意料之外，终归我们是玩儿尽兴了，不多陪，客官您上眼，后厨还有活儿，回见。 ");
        Article article3 = new Article(3,"晚安","大学第一次做兼职送外卖 跑了一天很累没有自行车全靠走 一遍遍的爬楼 汗把里面的衣服湿透 一单两块钱 跑了24单48块钱 结束回到寝室寝室没有人室友全出去和男朋友约会 自己回来开门的一瞬间挺落寞的但终究是抿嘴一笑 有男朋友有什么了不起 我没有男朋友但我过的很充实啊 晚安 都会好的");
        Article article4 = new Article(4,"不要说话","一听到“我以为你懂的每当我看着你”就难受。高中我喜欢的一个女生就总是那样看着我。我们关系挺好的，有点暧昧，甚至有共同的秘密。我以为她也喜欢我。过了三个学期，她和她以前初中的男的同班同学走得很近，然后我着急了，向她表白，才明白一直都是我错了。我以为我懂她，也以为她懂我——");
        Article article5 = new Article(5,"美好事物","今年大一 独自一人在合肥上寒假班 格外的孤独 未来还有很长的路要走 大一学会弹吉他 把自己四年的专业知识学完 大二考驾照 暑假公司实习 大三考教师资格证 未来几年我的梦想是为我的家人买一栋属于自己的房子 然后在自己喜欢的城市扎根 2018年2月16日写下这些话 给自己五年的期限 愿五年后梦成真");
        Article article6 = new Article(6,"小丑"," 从不怨恨，也不抱怨。过去的，就让它过去吧，无论怎样美好，怎样伤感，我们，始终回不到过去，走不进曾经。重要的是今天，明天。那些曾经的路，过去的人，都将成为驿站、成为过客，渐渐的，也会淡漠，也会遗忘");

        Article article2 = new Article(2,"大雁","有人喜欢，就有人不待见，平常不过何必执着，刻板印象不论好坏都好比顽石不碎，何况人无全优，自然也在情理之中不在意料之外，终归我们是玩儿尽兴了，不多陪，客官您上眼，后厨还有活儿，回见。 ");
        Document document = new Document();
        document.add(new Field("id",article2.getId().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("content",article2.getContent().toString(), Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("title",article2.getTitle().toString(), Field.Store.YES, Field.Index.ANALYZED));
        //本地目录
        Directory directory = FSDirectory.open(new File("f:/db"));
        //标准分析器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);

        IndexWriter indexWriter = new IndexWriter(directory,analyzer, IndexWriter.MaxFieldLength.LIMITED);
        indexWriter.addDocument(document);
        indexWriter.close();



        //指定分析器
    }
    //添加多个
    public  void addAllIndex() throws Exception{

        //实体类
        Article article1 = new Article(1,"我还爱你","真希望多年后的夜晚，我在厨房洗完餐具，你在客厅里挑选碟片，我拌好一盆蔬菜水果沙拉，你选好一部老旧电影，走到厨房把我抱到沙发上让我靠在你怀里而你懒散的靠在沙发背上，我们就看电影，说起话来没有话题，只是说出想说的零零总总，你说这样的日子很安稳，我说时间太快还想和你过上几百年。");
        Article article2 = new Article(2,"大雁","有人喜欢，就有人不待见，平常不过何必执着，刻板印象不论好坏都好比顽石不碎，何况人无全优，自然也在情理之中不在意料之外，终归我们是玩儿尽兴了，不多陪，客官您上眼，后厨还有活儿，回见。 ");
        Article article3 = new Article(3,"晚安","大学第一次做兼职送外卖 跑了一天很累没有自行车全靠走 一遍遍的爬楼 汗把里面的衣服湿透 一单两块钱 跑了24单48块钱 结束回到寝室寝室没有人室友全出去和男朋友约会 自己回来开门的一瞬间挺落寞的但终究是抿嘴一笑 有男朋友有什么了不起 我没有男朋友但我过的很充实啊 晚安 都会好的");
        Article article4 = new Article(4,"不要说话","一听到“我以为你懂的每当我看着你”就难受。高中我喜欢的一个女生就总是那样看着我。我们关系挺好的，有点暧昧，甚至有共同的秘密。我以为她也喜欢我。过了三个学期，她和她以前初中的男的同班同学走得很近，然后我着急了，向她表白，才明白一直都是我错了。我以为我懂她，也以为她懂我——");
        Article article5 = new Article(5,"美好事物","今年大一 独自一人在合肥上寒假班 格外的孤独 未来还有很长的路要走 大一学会弹吉他 把自己四年的专业知识学完 大二考驾照 暑假公司实习 大三考教师资格证 未来几年我的梦想是为我的家人买一栋属于自己的房子 然后在自己喜欢的城市扎根 2018年2月16日写下这些话 给自己五年的期限 愿五年后梦成真");
        Article article6 = new Article(6,"小丑"," 从不怨恨，也不抱怨。过去的，就让它过去吧，无论怎样美好，怎样伤感，我们，始终回不到过去，走不进曾经。重要的是今天，明天。那些曾经的路，过去的人，都将成为驿站、成为过客，渐渐的，也会淡漠，也会遗忘");
        //
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        //加入到document
        Document document1 = LuceneUtil.javabean2document(article1);
        Document document2 = LuceneUtil.javabean2document(article2);
        Document document3 = LuceneUtil.javabean2document(article3);
        Document document4 = LuceneUtil.javabean2document(article4);
        Document document5 = LuceneUtil.javabean2document(article5);
        Document document6 = LuceneUtil.javabean2document(article6);
        indexWriter.addDocument(document1);
        indexWriter.addDocument(document2);
        indexWriter.addDocument(document3);
        indexWriter.addDocument(document4);
        indexWriter.addDocument(document5);
        indexWriter.addDocument(document6);
        indexWriter.close();
    }

    //更新
    public void updateIndex(Article article) throws Exception {
        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        //
        Document document = LuceneUtil.javabean2document(article);
        Term term = new Term("id","1"); //修改的是id 更新id为1 的document对象
        /**
         * 1 需要更新的document的对象
         *
         * 2
         * */

        indexWriter.updateDocument(term,document);
        indexWriter.close();

    }
    //更新
    public void deleteIndex() throws Exception {

        IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
        //
        Term term = new Term("id","6"); //修改的是id 删除id为6 的document对象
        /**
         * 1 需要更新的document的对象
         * */
        indexWriter.deleteDocuments(term);
        indexWriter.close();


    }
    public void findindexdbkey(String key) throws IOException, ParseException {
        List<Article> list = new ArrayList<Article>();
        Version version = Version.LUCENE_30;
        Directory directory = FSDirectory.open(new File("f:/db"));
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
        TopDocs topDocs =  indexSearcher.search(query,Max); //
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
           // System.out.println(article.toString());
        }
        for(Article a:list){
            System.out.println( a.toString());
        }
    }
    public static void main(String[] args) throws Exception {

        curdDemo curd = new curdDemo();
        // curd.addAllIndex();
       // curd.findindexdbkey("我");
        Article article = new Article(1,"我还爱你","我还是一如既往的爱你");
        curd.updateIndex(article);
        curd.findindexdbkey("我");
    }

}
