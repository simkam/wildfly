/*
 *
 * Copyright ${YEAR} Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.jboss.as.test.compat.jpa.hibernate.transformer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.descriptor.java.BooleanTypeDescriptor;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class BooleanAbstractStandardBasicType extends AbstractStandardBasicType<Boolean> {

    public BooleanAbstractStandardBasicType() {
        this( org.hibernate.type.descriptor.sql.BooleanTypeDescriptor.INSTANCE, BooleanTypeDescriptor.INSTANCE );
    }

    public BooleanAbstractStandardBasicType(SqlTypeDescriptor sqlTypeDescriptor, JavaTypeDescriptor<Boolean> javaTypeDescriptor) {
        super( sqlTypeDescriptor, javaTypeDescriptor);
    }

    private static void internalSessionImplementorUsingMethod(SessionImplementor session) {
        session.isTransactionInProgress();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, boolean[] settable, SessionImplementor session) throws HibernateException, SQLException {
        internalSessionImplementorUsingMethod(session);
        if (value == null) {
            st.setNull( index, Types.BOOLEAN );
        }
        else {
            st.setBoolean( index, (Boolean) value );
        }
    }

    @Override
    public Object get(ResultSet rs, String name, SessionImplementor session) throws HibernateException, SQLException {
        return super.get(rs, name, session);
    }

    @Override
    public void set(PreparedStatement st, Boolean value, int index, SessionImplementor session) throws HibernateException, SQLException {
        super.set(st, value, index, session);
    }

    @Override
    public String getName() {
        return BooleanAbstractStandardBasicType.class.getName();
    }
}
