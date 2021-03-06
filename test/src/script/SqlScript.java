package script;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 10-15为原始人员方案配置设置默认值
 * @author pc
 *
 */
public class SqlScript {
	static Connection con=null;
	static Statement st=null;
	static ResultSet rs=null;
	public static void main(String[] args) throws Exception {
		String prodsDev[]={"2510000027940079","3","2510000013000424","2510000013000418","2510000013000427","13000000000000005","19400000000000003","2520000009941793","2510000009722011","33780000000000147"};
		String userIdsDev[]={"2510000027690614","2510000006760067","2510000030470084","2510000002270032","2510000002270007","2510000004730004","1","2510000000691132","2510000006330348","2510000008271101","53","61","55","54","74","73","71","67","2510000003760089","24640000000001031","2510000025950012","2510000000690292","2510000025700011","2510000003760082"};
//		String nodeIdsDev[]={"sid-E2790778-F970-44A8-BE44-6A6BC2183B6C","sid-D7FAE03F-C713-4F6E-BA92-940958F444D9","sid-68BA8A60-3423-4196-AE22-BC6344524E84","sid-6BD3EDD5-16EC-448F-B703-BA16220C587B","sid-025CA6C0-2292-4DBA-893E-672049A77460","sid-F3AC620F-A92E-47B1-BD7E-B7993ECDE198","sid-B62E56A3-21E3-4EA4-94D9-4134A8CADB32","sid-0C95971D-B63C-4C51-BA2E-185EA1A1C54A","sid-C9032EA9-9A26-4A4E-A7E9-0842798A4992","sid-D9849316-B28B-4811-9B97-D573B71B084E","sid-8715C470-2969-43DC-89D1-BD1F65480CE1","sid-5E31F028-2A8E-4D4E-B8FD-796F4DD73301","sid-1BFCC264-CDA1-4C3B-BFF4-D76C49BE180B","sid-1DF9118F-8BC8-4369-A8A5-72246314CB32","sid-DD913E6F-03CC-4202-8CD6-0611269DE443","sid-698E47F9-3012-40AA-962E-68732F811700","sid-9DF5BB97-888D-4049-8C55-5D44C77EE952","sid-3264037A-E2F5-4E9E-8C8F-646E8B0E652E","sid-1EC4024F-75C7-4DA4-90F5-8397FA11A522","sid-5FB21FAC-F2AB-481F-801D-7B7A9E1FA89D","sid-A62D1B8C-1EAC-426E-B1B5-61FC71C5EF73","sid-110A9745-1B9E-4A5E-9843-A68F4914F301","sid-28E237D8-C260-4CB8-A334-115E54AAFE2F","sid-6CB8EA36-67D2-4276-8594-A357B32BDD69","sid-7BF8B866-E5E5-4D6C-B2F3-55B2A9F0008A","sid-1DBCE454-7B79-4146-9A6F-76707925B450","sid-570FF449-6FF9-47BA-B12A-91CD3192C552","sid-C67D194E-96E2-49A9-945E-4696191A37E1","sid-E8F029EA-5B65-46DB-AA8F-6DD5795ECC7C","sid-0D332FE2-BA3C-4D46-B943-267110239FAA","sid-87FF04A3-31E8-450F-96DE-25CA0BFBF923","sid-A2B3C1AF-08B9-4F1B-B48A-70D48DAC4CD3","sid-4F8EB7DA-B2F6-483C-863C-C358C4E5056F","sid-D34B933A-4294-4BCE-B522-06385DCB53F7","sid-2A6422A6-5D31-4C78-8F67-5673055BD64B","sid-7CF190E8-04AA-4C0D-B229-D0A21BD420F5","sid-7730411A-9D3F-4C61-AFEE-9E1EAC93BACE","sid-01A0C4D5-2D9D-43E9-A045-E1D916535C29","sid-7BDFC39A-D2E0-4734-8864-647C4E28FC6F","sid-63498D76-6019-4FB5-B92E-D5F8B6CE1641","sid-A661FBCD-9569-40A2-A3CD-E3568B9B9079","sid-18840FAA-C0AB-4473-9E74-0A67702D7850","sid-2F9704DB-02D1-41D3-9E4F-9C49F1EA5697","sid-D98574C9-5C63-4CBC-9467-A16B60BC1927","sid-9231273A-26E5-4F5C-8B7D-C4032CBF45F3","sid-F8B9FEBA-2AAD-4455-8BCF-A9FB987D6BEC","sid-A5ED3018-4F27-42A5-B171-9CBCA231143C","sid-B3093CCB-361A-48DD-A336-929D07E39613","sid-8772A745-C333-4B2F-BAC2-D65FBEFE105E","sid-3019D9CD-96E0-4AC9-8194-D1FF55D2C380","sid-44877C38-57E2-482A-AE37-E2561C740BAD","sid-F841FDAB-8965-4CC9-8314-A70D04A5393F","sid-C6025AD6-417D-4CFF-BE99-263250B0C51B","sid-2ED4C452-A9E3-43F6-85C1-247D9C89086D","sid-35DFD4FC-2009-480A-9980-54C5DF017AF0","sid-594A89E7-023E-44A5-9101-1601EE381CC1","sid-DB6B4947-A8FF-40AF-93EF-983E36A8EE2B","sid-48AF6D4B-A22D-4877-91A6-6CE0A9FD1CAC","sid-970691D2-8091-4F6B-816C-AEC4EACFCD4D","sid-5BD20442-6046-4970-A97B-ADB5D8766826","sid-3F0F3917-7CFA-4420-9A08-E85693F7D02F","sid-0FCAD1C2-E6AB-451E-8FA3-3881A1BF2579","sid-A42F5952-B01C-4893-8CDB-29BB103B2A40","sid-1690F686-9F59-42ED-9CB8-F7DB80F75D61","sid-95321DE3-FE22-461C-800B-7CD53CBED30A","sid-3BF8C035-6639-438C-89AF-6103F8D44849"};
		String sLinkDev="jdbc:mysql://10.10.1.151:43572/liangce_loan_bpm?useUnicode=true&characterEncoding=utf-8";
		String uDev="mysqldb";
		String passDev="mysqldb";
		//测试
		String prodsTes[]={"2520000009941793","","2510000009722011","2510000013000427","2510000013000418","2510000013000424","2510000010800030","2530000012631302","2530000012631397","2530000012651372","2530000012702395","2530000012702402","2530000012940019","2530000015605777","2530000015844246","2530000016003012","2530000016003027","2530000016034583","2530000016040566","2530000016040837","2530000016051468","2530000016410006","2530000016410028","2530000016601987","2530000019790692","2530000019861257","2530000011786063","2530000020430907","2530000020430969","2530000016003004","2530000026200004","2530000026200153"};
		String userIdsTes[]={"2510000002270032","2510000002270007","2510000004730004","1","2510000000691132","2510000006330348","2510000008271101","53","2510000010762368","2510000006760067","61","55","74","2510000003760089","2510000010920560","73","2530000015841231","2530000015870420","2530000016001137","2530000015841247","2530000015990097","2530000016690934","2530000017430054","67","2530000017560723","2530000014830900","2530000013024906","2510000010762357","2530000015934584","2530000018466933","2530000018482969","2530000018484553","2530000018467024","2530000023001627","2530000023001721"};
		String sLinkTes="jdbc:mysql://10.10.1.15:3306/liangce_loan_bpm?useUnicode=true&characterEncoding=utf-8";
		String uTes="mysqldb";
		String passTes="mysqldb";
//		System.out.println(prodsTes.length);//10	32
//		System.out.println(userIdsTes.length);//24	35
		int programmerId=10000;
		String allSql="";
		for(int i=0;i<prodsTes.length;i++){
			for(int j=0;j<userIdsTes.length;j++){
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(sLinkTes, uTes, passTes);
				st=con.createStatement();

				String selStr="select count(*) from loan_deal_operator where PRODUCT_ID_='"+prodsTes[i]
						+"' and PRIMARY_USER_ID_='"+userIdsTes[j]+"';";
//				System.out.println(selStr);
				rs=st.executeQuery(selStr);
				
				while(rs.next()){
					if(rs.getInt(1)>0){

						String updSql="update loan_deal_operator set PROGRAMME_ID_='"+programmerId+"' where PRODUCT_ID_='"+prodsTes[i]
								+"' and PRIMARY_USER_ID_='"+userIdsTes[j]+"';";
						String insSql="INSERT INTO `liangce_loan_bpm`.`loan_deal_operator_programme` (`ID_`, `NAME_`, `TENANT_ID_`, `CREATE_TIME_`, `CREATE_BY_`, `UPDATE_TIME_`, `UPDATE_BY_`, `PRODUCT_ID_`, `PRIMARY_USER_ID_`) VALUES ('"
						+programmerId+"', '原始旧数据默认方案', '1', '2019-10-15 10:50:30', '1', '2019-10-15 10:57:03', '1', '"+prodsTes[i]+"', '"+userIdsTes[j]+"');";
						programmerId++;	
						allSql=allSql+updSql+"\n"+insSql+"\n";
					}
				}
			}
		}
		rs.close();
		st.close();
		con.close();
		System.out.println("开始输出文件");
		File file=new File("C:/Users/pc/Desktop/packet/test.txt");
		file.createNewFile();
		FileWriter fw=new FileWriter(file);
		fw.write(allSql);
		fw.close();
		System.out.println("文件输出成功");

	}
}
