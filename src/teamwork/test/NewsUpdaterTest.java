package teamwork.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

import teamwork.NewsUpdater;

public class NewsUpdaterTest {

  private static NewsUpdater newsUpdater;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    newsUpdater = new NewsUpdater();
  }

  @Test
  public void failToGetContent() {
    assertFalse(newsUpdater.connect("http://abc/"));
  }

  @Test
  public void guangmingSimpleTest() {
    newsUpdater
        .connect("http://epaper.gmw.cn/gmrb/html/2012-10/07/nw.D110000gmrb_20121007_7-01.htm?div=-1");
    String content = newsUpdater.getContent();
    String trueContent =
        "    本报福州10月6日电（记者高建进）福建省第六届“书香八闽”全民读书月暨第七届福州读书月活动今天在福建博物院启动。启动仪式上，有关领导为读书月活动志愿服务队授旗，向福州市10家道德讲堂赠送《福州道德典型》等书籍，向农家书屋、乡镇图书室、县图书馆、中小学图书馆、留守儿童代表捐赠图书，并开启“书香校园”手机阅读平台。本届“书香八闽”全民读书月以“弘扬福建精神，推动科学发展”为主题。活动期间，各地将开展优秀书目推荐、“书香人家”和图书援助等活动，以及“喜迎十八大”百种优秀图书展及精品诵读晚会等。\n";
    assertTrue(trueContent.equals(content));
  }

  @Test
  public void guangmingMultLineTest() {
    newsUpdater
        .connect("http://epaper.gmw.cn/gmrb/html/2011-12/29/nw.D110000gmrb_20111229_3-11.htm?div=-1");
    String content = newsUpdater.getContent();
    String trueContent =
        "    新华社北京12月28日电（记者白瀛、周玮）由文化部和国务院农民工工作联席会议办公室共同主办的“温暖之春”——2012年慰问全国农民工春节晚会28日在北京奥体中心体育馆举行。近3000名农民工观看了晚会。\n"
            + "    伴随着现场大屏幕上徐徐展开的一封由国务院农民工工作联席会议办公室和文化部致全国农民工的慰问信，晚会拉开帷幕。\n"
            + "    主办方说，晚会在参与主体、节目内容和晚会受众等各方面均充分考虑到农民工欣赏和参与文化活动的需求，体现出“农民工演，演农民工，演给农民工看”的特点。\n"
            + "    从参与主体看，参加晚会演出的400多名演员中，80%以上都是农民工或农民演员。来自广东省东莞市塘厦镇打工音乐创作基地几位农民工歌手，带来了《快乐打工的男孩女孩》《都市里的农民兄弟》《农民工我的兄弟姐妹》等“打工歌曲”。草根歌手“菜花甜妈”蔡洪平演唱了改编自世界名曲《今夜无人入睡》的《送你葱》，今年55岁的蔡洪平是来自安徽的卖菜大妈，因其惊人的音乐天赋轰动全国。来自百年职校的一百名农民工子弟学生合唱了《相信爱》，百年职校是一所专为农民工提供职业技能培训的公益学校，秉承“百年树人”的办学理念，办校七年来广受社会好评。\n"
            + "    从节目内容看，晚会主要以反映农民工最真实的情感、最迫切的诉求，再现农民工打工生活的节目为主。小品《寻家短信》讲述了春节将至，在某城市地铁工程上奋战的农民工们因赶工期不能回家过年，想用手机短信的方式各自寻找一个当地的家庭去过春节，受到城市居民热烈回应的动人故事。儿童舞蹈《流动娃》以幽默、生动、极具表现力的舞蹈语言，表现了从全国各地“坐着火车、跟着爸妈、去广东新家”的“流动孩子”幸福成长、获得平等读书机会的感人故事，表达了社会各界对农民工子弟、对留守儿童的关爱。\n"
            + "    从晚会受众看，晚会组织了近3000名各行各业的农民工代表到现场观看演出，并参与节目互动，他们当中有环卫工人、保安、保姆、小时工，有营业员、建筑工人、机械工人，都是在平凡岗位上默默奉献的普通一员。晚会将于春节期间在中央电视台播出。\n";
    assertTrue(trueContent.equals(content));
  }


  @Test
  public void guangmingSubHeadingTest() {
    newsUpdater
        .connect("http://epaper.gmw.cn/gmrb/html/2012-01/02/nw.D110000gmrb_20120102_4-01.htm?div=-1");
    String content = newsUpdater.getContent();
    String trueContent =
        "重庆 江津向困难户送礼包\n"
            + "    本报重庆1月1日电（通讯员袁孝春 记者张国圣）2011年12月31日，重庆市江津区启动了元旦春节“送温暖·迎新春”慰问活动。区委书记关海祥说，慰问活动将持续到龙年元宵节，将向13.1万困难户派发总价值5216万元的慰问礼包。慰问对象包括贫困党员、困难劳模、留守儿童、空巢老人等28类困难人员。\n"
            + "福建 海峡两岸焰火庆新年\n"
            + "    本报福州1月1日电(记者高建进)2012年元旦零时,福州连江与台湾马祖之间海域的马祖澳东西两岸,璀璨的焰火欢乐绽放,两岸数万民众同在美丽的夜空中迎接新年的到来。焰火升腾中,福州连江县长林峰和台湾马祖县长杨绥生还互通电话,共同祈福新年。\n"
            + "西宁 举办万人元旦环城赛\n"
            + "    本报西宁1月1日电(记者刘鹏)今天上午,甘肃西宁有近万名市民参加了西宁地区第三十九届元旦环城赛。今年西宁环城赛的主题是“幸福健康从脚下开始”,共有1100名运动员、80个方队一万多人参加,队伍中年龄最大的老者为83岁,最小的只有9岁。\n"
            + "山东 名家“风雅”贺新年\n"
            + "    本报济南1月1日电(记者赵秋丽 通讯员王立强)今天,山东省图书馆艺术展览馆内名家汇聚,梁修、王长水等书画名家在“风雅迎新年书画展”现场举行了一场笔会,以书会友，以画会友，共同挥毫祝福2012新年。本次书画展从2011年12月29日开幕,一直持续到2012年的1月4日。\n"
            + "南宁 屯卢坡球场建成开赛\n"
            + "    本报南宁1月1日电（通讯员黄升模、廖英晖 记者刘昆）南宁市邕宁区新江镇屯卢坡灯光球场今天投入使用，当地群众以球赛形式迎新年。屯卢坡灯光球场从2009年动工建设。2010年，由于资金不足等原因停工。2011年6月，在南宁市邕宁区政府财政的支持下，终于在元旦建成了这一项目。\n"
            + "银川 轮番上演新年音乐会\n"
            + "    本报银川1月1日电(记者庄电一)宁夏有两万多人是在欣赏新年音乐会中迎接2012年的:自七八年前引入新年音乐会以来,音乐会一年也没有“缺席”过。进入2011年12月下旬以来,德国莱比锡国家爱乐乐团等演出的高水平大型新年音乐会在银川接连上演了5场。\n";
    assertTrue(trueContent.equals(content));
  }

  @Test
  public void guangmingHaveImageTest() {
    newsUpdater
        .connect("http://epaper.gmw.cn/gmrb/html/2012-01/06/nw.D110000gmrb_20120106_6-10.htm?div=-1");
    String content = newsUpdater.getContent();
    String trueContent =
        "    新年伊始,江西省龙南县夹湖乡花树新村“留守儿童之家”充满欢笑声。针对农村留守儿童日益增多,该县在各乡镇均成立了“留守儿童之家”,通过开展形式多样的活动,在全县形成了关爱留守儿童健康成长的良好社会氛围。图为夹湖乡副书记廖洪清(右一)等与孩子们一起搭建玩具模型。本报记者高腾 通讯员李慧摄\n";
    assertTrue(trueContent.equals(content));
  }

  @Test
  public void sichuanMutiLineTest() {
    NewsUpdater newsUpdater = new NewsUpdater();
    newsUpdater.connect("http://epaper.scdaily.cn/shtml/scrb/20151229/119905.shtml");
    String content = newsUpdater.getContent();
    String trueContent =
        "    本报讯 （记者 张立东）12月28日，省检察院首次向媒体公布了尹某某等4人故意伤害案等十大典型案例。\n"
            + "    目前，我省未成年人数量占全国未成年人数量的1/15，农村留守儿童和城镇流动儿童数量均位居全国前列。“我省未成年人犯罪总体呈下降趋势，但以各种方式侵害未成年人权益的现象却大量出现。”省检察院副检察长张晓勇说。\n"
            + "    据张晓勇介绍，2013年新刑诉法实施以来，全省各地检察机关坚持依法从重从快打击性侵未成年人，拐卖、虐待儿童，教唆、引诱、组织、胁迫未成年人进行违法犯罪等侵害未成年人身心健康与合法权益的各类刑事犯罪活动。2013年至今，我省检察机关共起诉猥亵儿童罪377件379人，起诉拐骗儿童罪34件44人，起诉拐卖妇女、儿童罪116件234人。同时，坚持依法打击和注重保护相结合，全省各级检察机关及时告知未成年被害人及其法定代理人相关诉讼权利和案件进展情况，积极开展心理测评、心理抚慰和心理疏导工作，帮助未成年被害人走出心理阴影，对经济困难的未成年被害人进行法律援助和司法救助，取得了良好效果。\n"
            + "    我省不断加强未成年人刑事检察专业化建设，截至目前，全省21个市州院和成都铁路运输分院中有13个院、包括派驻院在内的187个区县院中有89个院成立了专门的未成年人刑事检察机构。\n";
    assertTrue(trueContent.equals(content));
  }

  @Test
  public void sichuanHaveImageTest() {
    NewsUpdater newsUpdater = new NewsUpdater();
    newsUpdater.connect("http://epaper.scdaily.cn/shtml/scrb/20140729/70638.shtml");
    String content = newsUpdater.getContent();
    String trueContent =
        "    近日，绵阳市平武县旧堡羌族乡庆林村，孩子们在参加西南石油大学记者团组织的“小世界杯”足球赛。庆林村是西南石油大学的大学生社会实践基地之一，每年大学生们定期来到庆林村开展暑期社会实践活动，专门为留守儿童开设暑期课程辅导和安全教育课，并设立贫困儿童奖助学金。 曹正 摄\n";
    assertTrue(trueContent.equals(content));
  }

}
