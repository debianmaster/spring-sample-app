node('')  {

        stage('Verify/Create Objects in DEV') {
            openshift.withProject( 'dev1' ) {
                def bc = openshift.selector( 'bc', [ app:'ssa' ] ).object()
                if(null == bc) {
                  def created = openshift.newApp( 'https://github.com/debianmaster/spring-sample-app.git','--name','ssa');
                  def appbc = created.narrow('bc')
                  sleep(3)
                  appbc.logs('-f')
                }
            }
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
