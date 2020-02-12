/**
 * 
 */
package org.cisco.asbp_role_responsibilites.util;

import java.io.Serializable;

import org.cisco.asbp_role_responsibilites.entity.Module;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;



/**
 * @author Narendra
 *
 */
public class ModuleSequenceGenerator extends SequenceStyleGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Module module = (Module) object;
		if(module.getId()!=null) {
			return module.getId();
		}
		return super.generate(session, object);
	}
}
