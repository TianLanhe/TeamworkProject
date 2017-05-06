package teamwork.controler;

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
    c.addTag(new Tag("�ѷ���"));
    c.addTag(new Tag("δ����"));
    catalog.add(c);

    c = new NewsClass("��ֽ���");
    c.addTag(new Tag("���뵳��"));
    c.addTag(new Tag("ʡ������"));
    c.addTag(new Tag("���е���"));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("�ظ�����д"));
    c.addTag(new Tag("����"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("�����ձ�"));
    c.addTag(new Tag("�Ĵ��ձ�"));
    c.addTag(new Tag("�Ϸ����б�"));
    catalog.add(c);

    c = new NewsClass("��������");
    c.addTag(new Tag("������ذ�"));
    c.addTag(new Tag("�����뿴��"));
    c.addTag(new Tag("���õ�λ�����"));
    c.addTag(new Tag("���ض�ͯ���ܱ���"));
    c.addTag(new Tag("���ض�ͯ����(��)����"));
    c.addTag(new Tag("���ض�ͯ����"));
    c.addTag(new Tag("���ض�ͯ��������"));
    c.addTag(new Tag("���ض�ͯŬ���Ͻ�"));
    c.addTag(new Tag("�򹤸�ĸ��������"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("������Ϣ��Դ");
    c.addTag(new Tag("����"));
    c.addTag(new Tag("����"));
    c.addTag(new Tag("��ҵ"));
    c.addTag(new Tag("��ҵ��λ"));
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("ר��ѧ��"));
    c.addTag(new Tag("�����쵼����Э���˴����"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("ý���������");
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("����Ҹ�"));
    c.addTag(new Tag("�����ͯ"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("�������ŵľ�������");
    c.addTag(new Tag("����һ���Ծ���"));
    c.addTag(new Tag("���λ������Ŀ"));
    c.addTag(new Tag("��ѿ���"));
    c.addTag(new Tag("����������Ŀ"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("��������������");
    c.addTag(new Tag("����"));
    c.addTag(new Tag("��ҵ"));
    c.addTag(new Tag("��ҵ��λ"));
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("����������������");
    c.addTag(new Tag("����"));
    c.addTag(new Tag("��ҵ"));
    c.addTag(new Tag("��ҵ��λ"));
    c.addTag(new Tag("��������"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("�����ڳ��ж����ԭ��");
    c.addTag(new Tag("�ޱ��ػ���"));
    c.addTag(new Tag("˽��ѧУѧ�Ѹ�"));
    c.addTag(new Tag("˽��ѧУ������"));
    c.addTag(new Tag("˽��ѧУ��ȡ��"));
    c.addTag(new Tag("����"));
    catalog.add(c);

    c = new NewsClass("�Ա�");
    c.addTag(new Tag("��"));
    c.addTag(new Tag("Ů"));
    catalog.add(c);

    catalog.get("��������").getTag("������ذ�").setRelationClass(catalog.get("�������ŵľ�������"));
    catalog.get("��������").getTag("���ض�ͯ���ܱ���").setRelationClass(catalog.get("�Ա�"));
    catalog.get("��������").getTag("���ض�ͯ����(��)����").setRelationClass(catalog.get("�Ա�"));
    catalog.get("��������").getTag("���ض�ͯ��������").setRelationClass(catalog.get("�Ա�"));
    catalog.get("ý���������").getTag("��������").setRelationClass(catalog.get("�Ա�"));
  }
}
