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
    NewsClass c = new NewsClass("�Ƿ����");
    c.addTag(new Tag("�ѷ���", c));
    c.addTag(new Tag("δ����", c));
    catalog.add(c);

    c = new NewsClass("��ֽ���");
    c.addTag(new Tag("���뵳��", c));
    c.addTag(new Tag("ʡ������", c));
    c.addTag(new Tag("���е���", c));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("�ظ�����д", c));
    c.addTag(new Tag("����", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("�����ձ�", c));
    c.addTag(new Tag("�Ĵ��ձ�", c));
    c.addTag(new Tag("�Ϸ����б�", c));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("������ذ�", c));
    c.addTag(new Tag("�����뿴��", c));
    c.addTag(new Tag("���õ�λ�����", c));
    c.addTag(new Tag("���ض�ͯ���ܱ���", c));
    c.addTag(new Tag("���ض�ͯ����(��)����", c));
    c.addTag(new Tag("���ض�ͯ����", c));
    c.addTag(new Tag("���ض�ͯ��������", c));
    c.addTag(new Tag("���ض�ͯŬ���Ͻ�", c));
    c.addTag(new Tag("�򹤸�ĸ��������", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("������Ϣ��Դ");
    c.addTag(new Tag("����", c));
    c.addTag(new Tag("����", c));
    c.addTag(new Tag("��ҵ", c));
    c.addTag(new Tag("��ҵ��λ", c));
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("ר��ѧ��", c));
    c.addTag(new Tag("�����쵼����Э���˴����", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("ý���������");
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("����Ҹ�", c));
    c.addTag(new Tag("�����ͯ", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("�������ŵľ�������");
    c.addTag(new Tag("����һ���Ծ���", c));
    c.addTag(new Tag("���λ������Ŀ", c));
    c.addTag(new Tag("��ѿ���", c));
    c.addTag(new Tag("����������Ŀ", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("��������������");
    c.addTag(new Tag("����", c));
    c.addTag(new Tag("��ҵ", c));
    c.addTag(new Tag("��ҵ��λ", c));
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("����������������");
    c.addTag(new Tag("����", c));
    c.addTag(new Tag("��ҵ", c));
    c.addTag(new Tag("��ҵ��λ", c));
    c.addTag(new Tag("��������", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("�����ڳ��ж����ԭ��",true);//��ѡ���
    c.addTag(new Tag("�ޱ��ػ���", c));
    c.addTag(new Tag("˽��ѧУѧ�Ѹ�", c));
    c.addTag(new Tag("˽��ѧУ������", c));
    c.addTag(new Tag("˽��ѧУ��ȡ��", c));
    c.addTag(new Tag("����", c));
    catalog.add(c);

    c = new NewsClass("�Ա�");
    c.addTag(new Tag("��", c));
    c.addTag(new Tag("Ů", c));
    catalog.add(c);

    catalog.get("��������").getTag("������ذ�").setNextClass(catalog.get("�������ŵľ�������"));
    catalog.get("��������").getTag("���ض�ͯ���ܱ���").setNextClass(catalog.get("�Ա�"));
    catalog.get("��������").getTag("���ض�ͯ����(��)����").setNextClass(catalog.get("�Ա�"));
    catalog.get("��������").getTag("���ض�ͯ��������").setNextClass(catalog.get("�Ա�"));
    catalog.get("ý���������").getTag("��������").setNextClass(catalog.get("�Ա�"));
  }
}
