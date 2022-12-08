import groovy.json.JsonSlurper

def reposJSON = new JsonSlurper().parseText(readFileFromWorkspace('repos.json'))

reposJSON.repos.each {
    createPipeline(it)
}

void createPipeline(it) {

    def jobname = it.jobname
    def gitrepo = it.gitrepo
    def desc = it.description
    pipelineJob(jobname) {
        description(desc)
        parameters {
            choiceParam('Happy', ['Yes (default)', 'Sure', 'option 3'], 'Of course')
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitrepo)
                        }
                    }
                }
            }
        }
    }
}
