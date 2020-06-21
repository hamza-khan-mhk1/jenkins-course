job('NodeJS Docker example') {
    scm {
        git('git://github.com/hamza-khan-mhk1/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('MHK DSL User')
            node / gitConfigEmail('newinternetman@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('hamzakhanmhk1/jenkins-dsl-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
