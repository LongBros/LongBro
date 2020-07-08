<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign comment=model.tabComment>
<#assign system=vars.system>
<#assign domain=vars.domain>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
<#assign pkType=func.getPkType(model)>
<#assign fkType=func.getFkType(model)>
package ${system}.${domain}.${package}.dao;

import java.util.List;
import ${system}.${domain}.${package}.bean.${class};
import ${system}.${domain}.${package}.dao.${class}Dao;
import ${system}.${domain}.${package}.common.BaseDao;

import org.springframework.stereotype.Repository;
/**
 * 描述：${comment} 
 <#if vars.developer?exists>
 * 作者:${vars.developer}
 </#if>
 * 日期:${date?string("yyyy-MM-dd HH:mm:ss")}
 * 版权：${vars.company}
 */
@Repository
public class ${class}Dao extends BaseDao{

	public String getNamespace() {
		return ${class}.class.getName();
	}
	public void create(${class} bean) {
		this.insert(getNamespace()+".create", bean);
	}
}

