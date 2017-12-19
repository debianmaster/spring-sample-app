node {
  stage 'build in development'
  openshiftBuild(namespace: 'dev', buildConfig: 'ssa', showBuildLogs: 'true')
  
  stage 'deploy in development'
  openshiftDeploy(namespace: 'dev', deploymentConfig: 'ssa')
  openshiftTag(namespace: 'dev', sourceStream: 'ssa',  sourceTag: 'latest', destinationStream: 'ssa', destinationTag: 'promoteToQA', destinationNamespace: 'dev')

  stage 'deploy in QA'
  openshiftDeploy(namespace: 'qa', deploymentConfig: 'ssa')

  stage 'scale dev and QA'
  openshiftScale(namespace: 'dev', deploymentConfig: 'ssa',replicaCount: '2')
  openshiftScale(namespace: 'qa', deploymentConfig: 'ssa',replicaCount: '3')
}
