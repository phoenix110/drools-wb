/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.jcr2vfsmigration;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeldTest {

    @Test
    public void testWeldContainer() {
        try {
            Weld weld = new Weld();
            WeldContainer weldContainer = weld.initialize();
            JcrExporter jcrExporter = weldContainer.instance().select( JcrExporter.class ).get();
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( "Error in weld container initialization; check dependencies!" );
        }
    }

}
