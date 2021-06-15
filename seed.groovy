 folder ('CI-Pipelines') {
   displayName('CI_pipelines')
   description('CI_pipelines')
   }
 def component=["frontend","catalogue","user","payment","shipping","cart"];
 def count=(component.size()-1)
 for (i in 0..count) {
     def j=component[i]
     println(j);
     pipelineJob("CI-Pipelines/${j}-ci") {
         println("CI-Pipelines/${j}-ci");
         configure { flowdefinition ->
             flowdefinition / 'properties' << 'org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty' {
                 'triggers' {
                     'hudson.triggeers.SCMTrigger' {
                         'spec'('*/2 * * * 1-5')
                         'ignore PostCommitHooks'(false)
                     }
                 }
             }

             flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                 'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                     'userRemoteConfigs' {
                         'hudson.plugins.git.UserRemoteConfig' {
                             'url'('https://github.com/Geetha1303/'+j+'.git')
                             println('https://github.com/Geetha1303/'+j+'.git')
                         }
                     }
                     'branches' {
                         'hudson.plugins.git.BranchSpec' {
                             'name' ('*/main')
                         }
                     }
                 }
                 'scriptpath'('jenkinsfile')
                 'lightweight' (true)
             }
         }

     }
 }
