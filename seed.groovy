 folder {'CI-Pipelines'} {
   displayname{'CI pipelines'}
   description{'CI pipelines'}
   }
pipelinejob{'CI-Pipelines/frontend-ci'} {
 configure { Flowdefinition ->
  Flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin: 'workflow-cps') {
   'scm' { $class: 'hudson.plugins.git.GitSCM'} {
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