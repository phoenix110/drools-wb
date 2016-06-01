/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.listbox;

import com.google.gwt.user.client.ui.ListBox;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableView;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;

public class ListBoxDoubleSingletonDOMElementFactory extends ListBoxSingletonDOMElementFactory<Double, ListBox> {

    public ListBoxDoubleSingletonDOMElementFactory( final GridLayer gridLayer,
                                                    final GuidedDecisionTableView gridWidget ) {
        super( gridLayer,
               gridWidget );
    }

    @Override
    public ListBox createWidget() {
        final ListBox listBox = new ListBox();
        return listBox;
    }

    @Override
    public String convert( final Double value ) {
        return value.toString();
    }

    @Override
    public Double convert( final String value ) {
        try {
            return new Double( value );
        } catch ( NumberFormatException nfe ) {
            return new Double( "0" );
        }
    }

}
