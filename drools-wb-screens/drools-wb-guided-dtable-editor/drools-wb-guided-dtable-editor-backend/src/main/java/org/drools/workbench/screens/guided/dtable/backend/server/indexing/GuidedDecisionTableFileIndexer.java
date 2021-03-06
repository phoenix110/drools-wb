/*
 * Copyright 2014 JBoss, by Red Hat, Inc
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
package org.drools.workbench.screens.guided.dtable.backend.server.indexing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.screens.guided.dtable.type.GuidedDTableResourceTypeDefinition;
import org.kie.workbench.common.services.refactoring.backend.server.indexing.AbstractFileIndexer;
import org.kie.workbench.common.services.refactoring.backend.server.indexing.DefaultIndexBuilder;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.java.nio.file.Path;

@ApplicationScoped
public class GuidedDecisionTableFileIndexer extends AbstractFileIndexer {

    @Inject
    protected GuidedDTableResourceTypeDefinition type;

    @Override
    public boolean supportsPath( final Path path ) {
        return type.accept( Paths.convert( path ) );
    }

    @Override
    public DefaultIndexBuilder fillIndexBuilder( final Path path ) throws Exception {
        final String content = ioService.readAllString( path );
        final GuidedDecisionTable52 model = GuidedDTXMLPersistence.getInstance().unmarshal( content );

        final DefaultIndexBuilder builder = getIndexBuilder(path);
        if( builder == null ) {
            return null;
        }

        final GuidedDecisionTableModelIndexVisitor visitor = new GuidedDecisionTableModelIndexVisitor( builder, model );
        visitor.visit();
        addReferencedResourcesToIndexBuilder(builder, visitor);

        return builder;
    }

}
