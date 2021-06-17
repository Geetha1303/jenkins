 folder ('PCIPipelines') {
   displayName('PCIPipelines')
   description('PCIPipelines')
   }
 //def component=["frontend"];
 //def count=(component.size()-1)
 //for (i in 0..count) {
    // def j=component[i]
     //println(j);
     pipelineJob('PCIPipelines/frontend-ci') {
         //println("CI-Pipelines/${j}-ci")
         configure { flowdefinition ->
            // flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
                // 'triggers' {
                   //  'hudson.triggeers.SCMTrigger' {
                     //    'spec'('*/2 * * * 1-5')
                     //    'ignore PostCommitHooks'(false)
                   //  }
               //  }
          //   }

             flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                 'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                     'userRemoteConfigs' {
                         'hudson.plugins.git.UserRemoteConfig' {
                             'url'('https://github.com/Geetha1303/frontend.git')
                            // println('https://github.com/Geetha1303/frontend.git')
                         }
                     }
                     'branches' {
                         'hudson.plugins.git.BranchSpec' {
                             'name' ('*/main')
                         }
                     }
                 }
                 'scriptPath'('Jenkinsfile')
                 'lightweight' (true)
             }
         }

     }
// }
