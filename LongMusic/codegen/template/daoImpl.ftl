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

/**
 * 
 * <pre> 
 * 描述：${comment} DAO接口
 <#if vars.developer?exists>
 * 作者:${vars.developer}
 </#if>
 * 日期:${date?string("yyyy-MM-dd HH:mm:ss")}
 * 版权：${vars.company}
 * </pre>
 */
package ${domain}.${system}.${package}.daoImpl;

import java.util.List;
import ${domain}.${system}.${package}.bean.${class};
import org.springframework.stereotype.Repository;

@Repository
public class ${class}DaoImpl extends BaseDao implements ${class}Dao {

	@Override
	public String getNamespace() {
		return ${class}.class.getName();
	}
	
}

