/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.services.verifier.core.checks;

import java.util.Arrays;
import java.util.HashSet;

import org.drools.workbench.services.verifier.api.client.index.ObjectField;
import org.drools.workbench.services.verifier.api.client.maps.util.RedundancyResult;
import org.drools.workbench.services.verifier.api.client.reporting.CheckType;
import org.drools.workbench.services.verifier.api.client.reporting.Issue;
import org.drools.workbench.services.verifier.api.client.reporting.Severity;
import org.drools.workbench.services.verifier.api.client.reporting.ValueForActionIsSetTwiceIssue;
import org.drools.workbench.services.verifier.core.cache.inspectors.PatternInspector;
import org.drools.workbench.services.verifier.core.cache.inspectors.RuleInspector;
import org.drools.workbench.services.verifier.core.cache.inspectors.action.ActionInspector;

public class DetectRedundantActionValueCheck
        extends DetectRedundantActionBase {

    public DetectRedundantActionValueCheck( final RuleInspector ruleInspector ) {
        super( ruleInspector,
               CheckType.VALUE_FOR_ACTION_IS_SET_TWICE );
    }

    @Override
    public void check() {
        hasIssues = false;

        for ( final PatternInspector patternInspector : ruleInspector.getPatternsInspector() ) {
            this.patternInspector = patternInspector;
            result = patternInspector.getActionsInspector()
                    .hasRedundancy();
            if ( result.isTrue()
                    && patternInspector.getPattern()
                    .getBoundName() == null ) {
                hasIssues = true;
                return;
            }
        }
    }

    @Override
    public Issue getIssue() {
        return new ValueForActionIsSetTwiceIssue( Severity.WARNING,
                                                  checkType,
                                                  result.get( 0 )
                                                          .toHumanReadableString(),
                                                  result.get( 1 )
                                                          .toHumanReadableString(),
                                                  new HashSet<>( Arrays.asList( ruleInspector.getRowIndex() + 1 ) ) );
    }

}
