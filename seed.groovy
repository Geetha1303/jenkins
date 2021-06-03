 folder ('CI-Pipelines') {
   displayName('CI_pipelines')
   description('CI_pipelines')
   }
pipelineJob('CI-Pipelines/frontend-ci') {
 configure { flowdefinition ->
   flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin: 'workflow-cps') {
   'scm'(class: 'hudson.plugins.git.GitSCM',plugin: 'git') {
     'userRemoteConfigs' {
       'hudson.plugins.git.UserRemoteConfig' {
         'url'{'https://github.com/Geetha1303/fontend.git'}
        }
      }
      'branches' {
        'hudson.plugins.git.BranchSpec' {
          'name' {'*/main'}
         }
      }
   }
   'scriptpath'{'jenkinsfile'}
   'lightweight'{true}
   }
  }
}