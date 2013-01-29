/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.activiti.engine.impl.bpmn.parser.handler;

import org.activiti.bpmn.constants.BpmnXMLConstants;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.Task;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;


/**
 * @author Joram Barrez
 */
public class TaskParseHandler extends AbstractMultiInstanceEnabledParseHandler<Task> {
   
  public Class< ? extends BaseElement> getHandledType() {
    return Task.class;
  }
  
  protected void executeParse(BpmnParse bpmnParse, Task task, ScopeImpl scope, ActivityImpl activityImpl, SubProcess subProcess) {
    ActivityImpl activity = createActivityOnScope(bpmnParse, task, BpmnXMLConstants.ELEMENT_TASK, scope);
    activity.setActivityBehavior(bpmnParse.getActivityBehaviorFactory().createTaskActivityBehavior(task));
    
    activity.setAsync(task.isAsynchronous());
    activity.setExclusive(!task.isNotExclusive());

    createExecutionListenersOnScope(bpmnParse, task.getExecutionListeners(), activity);
  }

}