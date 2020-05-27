package plugins;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultCommentGenerator;



public class MyCommentGenerator extends DefaultCommentGenerator {


  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    // 添加字段注释
    StringBuffer sb = new StringBuffer();
    field.addJavaDocLine("/**");
    if (introspectedColumn.getRemarks() != null)
      field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
    sb.append(" * 字段 : ");
    sb.append(introspectedColumn.getActualColumnName());
    field.addJavaDocLine(sb.toString());
    field.addJavaDocLine(" */");
  }


}