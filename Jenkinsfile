node('') {
          stage 'buildInDevelopment'
            openshiftBuild(namespace:'dev',buildConfig: 'welcome', showBuildLogs: 'true')
          stage 'deployInDevelopment'
            openshiftDeploy(namespace:'dev',deploymentConfig: 'welcome')
            openshiftScale(namespace:'dev',deploymentConfig: 'welcome',replicaCount: '5')
          stage 'deployInQA'
            input 'Proceeed ?'
            openshiftTag(namespace: 'dev', sourceStream: 'welcome',  sourceTag: 'latest', destinationStream: 'welcome', destinationTag: 'promoteToQA')
            openshiftDeploy(namespace:'qa',deploymentConfig: 'welcome')
            openshiftScale(namespace:'qa',deploymentConfig: 'welcome',replicaCount: '5')
}
