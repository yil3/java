package entity.handler;

import entity.User;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;


public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

  private User user;
  // 开始解析某行触发
  @Override
  public void startRow(int i) {
    if (i >0 ) user = new User();
  }

  // 结束某行解析触发
  @Override
  public void endRow(int i) {
   if (user != null) System.out.println(user);
  }

  // 对某行内的每个单元格进行处理
  @Override
  public void cell(String cellTitle, String value, XSSFComment xssfComment) {
    if (user != null) {
      // 截取第一个字母
      String substring = cellTitle.substring(0, 1);
      switch (substring) {
        case "A":
          user.setId(Long.valueOf(value));
          break;
        case "B":
          user.setUsername(value);
          break;
        case "C":
          user.setPassword(value);
          break;
        default:
          break;
      }
    }
  }
}
