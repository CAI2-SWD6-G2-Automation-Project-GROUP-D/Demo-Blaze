def buildApp() {
    echo "🔧 Building the automation project with Maven..."
    dir('DEPI.demoblaze') {  // Adjust this to match the actual folder name
        bat 'mvn clean compile'
    }
}

def testApp() {
    echo "🧪 Running TestNG tests..."
    dir('DEPI.demoblaze') {
        bat 'mvn test'
    }
}

def deployApp() {
    echo "📦 Archiving reports and logs..."
    dir('DEPI.demoblaze') {
        junit '**/target/surefire-reports/*.xml'
        archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/**/*.log, **/target/screenshots/**'
    }
}

return this
