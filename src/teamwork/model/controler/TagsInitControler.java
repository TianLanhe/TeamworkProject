package teamwork.model.controler;

import teamwork.model.ClassCatalog;
import teamwork.model.NewsClass;
import teamwork.model.Tag;

public class TagsInitControler {

  private ClassCatalog catalog;

  public TagsInitControler(ClassCatalog instance) {
    catalog = instance;
  }

  public void init() {
    NewsClass c = new NewsClass("是否分类");
    c.addTag(new Tag("已分类", c));
    c.addTag(new Tag("未分类", c));
    catalog.add(c);

    c = new NewsClass("报纸类别");
    c.addTag(new Tag("中央党报", c));
    c.addTag(new Tag("省级党报", c));
    c.addTag(new Tag("都市党报", c));
    catalog.add(c);

    c = new NewsClass("新闻类型");
    c.addTag(new Tag("纯净新闻", c));
    c.addTag(new Tag("特稿与特写", c));
    c.addTag(new Tag("评论", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("报道数量");
    c.addTag(new Tag("光明日报", c));
    c.addTag(new Tag("四川日报", c));
    c.addTag(new Tag("南方都市报", c));
    catalog.add(c);

    c = new NewsClass("报道主题");
    c.addTag(new Tag("帮助与关爱", c));
    c.addTag(new Tag("建议与看法", c));
    c.addTag(new Tag("表彰单位与个人", c));
    c.addTag(new Tag("留守儿童遭受暴力", c));
    c.addTag(new Tag("留守儿童遭受(性)暴力", c));
    c.addTag(new Tag("留守儿童犯罪", c));
    c.addTag(new Tag("留守儿童意外死亡", c));
    c.addTag(new Tag("留守儿童努力上进", c));
    c.addTag(new Tag("打工父母艰难生活", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("报道消息来源");
    c.addTag(new Tag("记者", c));
    c.addTag(new Tag("政府", c));
    c.addTag(new Tag("企业", c));
    c.addTag(new Tag("事业单位", c));
    c.addTag(new Tag("公益团体", c));
    c.addTag(new Tag("专家学者", c));
    c.addTag(new Tag("政府领导、政协或人大代表", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("媒介形象呈现");
    c.addTag(new Tag("积极健康", c));
    c.addTag(new Tag("可怜悲惨", c));
    c.addTag(new Tag("沐恩幸福", c));
    c.addTag(new Tag("问题儿童", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("帮助新闻的具体种类");
    c.addTag(new Tag("单纯一次性捐物", c));
    c.addTag(new Tag("旅游活动安排项目", c));
    c.addTag(new Tag("免费开放", c));
    c.addTag(new Tag("长期资助项目", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("帮助类新闻主体");
    c.addTag(new Tag("政府", c));
    c.addTag(new Tag("企业", c));
    c.addTag(new Tag("事业单位", c));
    c.addTag(new Tag("公益团体", c));
    c.addTag(new Tag("个人", c));
    catalog.add(c);

    c = new NewsClass("表彰类类新闻主体");
    c.addTag(new Tag("政府", c));
    c.addTag(new Tag("企业", c));
    c.addTag(new Tag("事业单位", c));
    c.addTag(new Tag("公益团体", c));
    c.addTag(new Tag("个人", c));
    catalog.add(c);

    c = new NewsClass("不能在城市读书的原因",true);//多选类别
    c.addTag(new Tag("无本地户籍", c));
    c.addTag(new Tag("私立学校学费高", c));
    c.addTag(new Tag("私立学校质量差", c));
    c.addTag(new Tag("私立学校被取消", c));
    c.addTag(new Tag("其他", c));
    catalog.add(c);

    c = new NewsClass("性别");
    c.addTag(new Tag("男", c));
    c.addTag(new Tag("女", c));
    catalog.add(c);

    catalog.get("报道主题").getTag("帮助与关爱").setNextClass(catalog.get("帮助新闻的具体种类"));
    catalog.get("报道主题").getTag("留守儿童遭受暴力").setNextClass(catalog.get("性别"));
    catalog.get("报道主题").getTag("留守儿童遭受(性)暴力").setNextClass(catalog.get("性别"));
    catalog.get("报道主题").getTag("留守儿童意外死亡").setNextClass(catalog.get("性别"));
    catalog.get("媒介形象呈现").getTag("积极健康").setNextClass(catalog.get("性别"));
  }
}
