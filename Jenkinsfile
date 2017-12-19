node('')  {
    openshift.withCluster() {
        stage('Verify/Create Objects in DEV') {
           
        }
        stage('Verify / Create objects in QA') {
            openshift.withProject( 'dev1' ) {
                def welcome = openshift.selector( 'dc', [ app: 'ssa' ] )
                def welcomeObjects = welcome.objects( exportable:true )
                openshift.withProject( 'qa1' ) {
                     openshift.create( welcomeObjects )
                }
            }
        } 
        stage('Build') {
            openshift.withProject( 'dev1' ) {
                 def bc = openshift.selector( 'bc', [ app:'ssa' ] )
                 bc.startBuild()
                 sleep(3)
                 bc.logs('-f')
            }
        }
        stage('Relaset to QA') {
            openshift.withProject( 'qa1' ) {
                 
            }
        }       
    }
}
