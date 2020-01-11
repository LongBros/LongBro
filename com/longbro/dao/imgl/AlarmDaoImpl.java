
/**
 * 
 * <pre> 
 * 描述：闹铃表(Alarm) DAO接口
 * 作者:longbro
 * 日期:2019-10-01 00:41:05
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
package longbro.com..daoImpl;

import java.util.List;
import longbro.com..bean.Alarm;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmDaoImpl extends BaseDao implements AlarmDao {

	@Override
	public String getNamespace() {
		return Alarm.class.getName();
	}
	
}

