def buildApp() {
    echo "🔧 Building the automation project with Maven..."
    bat 'mvn clean compile'
}

def testApp() {
    echo "🧪 Running TestNG tests..."
    bat 'mvn test'
}

def deployApp() {
    echo "📦 Archiving reports and logs..."

    junit '**/target/surefire-reports/*.xml'
    archiveArtifacts artifacts: '**/target/**/*.log, **/target/screenshots/**', fingerprint: true
}

return this
