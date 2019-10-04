



package longbro.com..entity;

import longbro.core.entity.BaseTenantEntity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import longbro.core.constants.MBoolean;
import longbro.core.annotion.table.FieldDefine;
import longbro.core.annotion.table.TableDefine;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  
 * 描述：闹铃表(Alarm)实体类定义
 * 作者：longbro
 * 邮箱: 1459892910@qq.com
 * 日期:2019-10-01 00:41:05
 * 版权：多啦学娱网络科技有限公司
 * </pre>
 */
@TableDefine(title = "闹铃表(Alarm)")
public class Alarm extends BaseTenantEntity {

	@FieldDefine(title = "闹铃编号")
	@Id
	@Column(name = "a_Id")
	protected Integer AId;

	@FieldDefine(title = "闹铃所属用户")
	@Column(name = "a_UserId")
	protected String AUserid; 
	@FieldDefine(title = "闹铃时间")
	@Column(name = "a_Time")
	protected String ATime; 
	@FieldDefine(title = "闹铃提示语")
	@Column(name = "a_Tips")
	protected String ATips; 
	@FieldDefine(title = "闹铃音乐")
	@Column(name = "a_Music")
	protected String AMusic; 
	@FieldDefine(title = "闹铃状态-开启，关闭，删除")
	@Column(name = "a_Status")
	protected Integer AStatus; 
	@FieldDefine(title = "创建时间")
	@Column(name = "a_CreateTime")
	protected String ACreatetime; 
	
	
	
	
	
	public Alarm() {
	}

	/**
	 * Default Key Fields Constructor for class Orders
	 */
	public Alarm(Integer in_id) {
		this.setPkId(in_id);
	}
	
	@Override
	public String getIdentifyLabel() {
		return this.AId;
	}

	@Override
	public Serializable getPkId() {
		return this.AId;
	}

	@Override
	public void setPkId(Serializable pkId) {
		this.AId = (String) pkId;
	}
	
	public Integer getAId() {
		return this.AId;
	}

	
	public void setAId(Integer aValue) {
		this.AId = aValue;
	}
	
	public void setAUserid(String AUserid) {
		this.AUserid = AUserid;
	}
	
	/**
	 * 返回 闹铃所属用户
	 * @return
	 */
	public String getAUserid() {
		return this.AUserid;
	}
	public void setATime(String ATime) {
		this.ATime = ATime;
	}
	
	/**
	 * 返回 闹铃时间
	 * @return
	 */
	public String getATime() {
		return this.ATime;
	}
	public void setATips(String ATips) {
		this.ATips = ATips;
	}
	
	/**
	 * 返回 闹铃提示语
	 * @return
	 */
	public String getATips() {
		return this.ATips;
	}
	public void setAMusic(String AMusic) {
		this.AMusic = AMusic;
	}
	
	/**
	 * 返回 闹铃音乐
	 * @return
	 */
	public String getAMusic() {
		return this.AMusic;
	}
	public void setAStatus(Integer AStatus) {
		this.AStatus = AStatus;
	}
	
	/**
	 * 返回 闹铃状态-开启，关闭，删除
	 * @return
	 */
	public Integer getAStatus() {
		return this.AStatus;
	}
	public void setACreatetime(String ACreatetime) {
		this.ACreatetime = ACreatetime;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public String getACreatetime() {
		return this.ACreatetime;
	}
	
	
	
	
		

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Alarm)) {
			return false;
		}
		Alarm rhs = (Alarm) object;
		return new EqualsBuilder()
		.append(this.AId, rhs.AId) 
		.append(this.AUserid, rhs.AUserid) 
		.append(this.ATime, rhs.ATime) 
		.append(this.ATips, rhs.ATips) 
		.append(this.AMusic, rhs.AMusic) 
		.append(this.AStatus, rhs.AStatus) 
		.append(this.ACreatetime, rhs.ACreatetime) 
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.AId) 
		.append(this.AUserid) 
		.append(this.ATime) 
		.append(this.ATips) 
		.append(this.AMusic) 
		.append(this.AStatus) 
		.append(this.ACreatetime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("AId", this.AId) 
				.append("AUserid", this.AUserid) 
				.append("ATime", this.ATime) 
				.append("ATips", this.ATips) 
				.append("AMusic", this.AMusic) 
				.append("AStatus", this.AStatus) 
				.append("ACreatetime", this.ACreatetime) 
		.toString();
	}

}



