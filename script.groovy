def buildApp() {
    echo "🔧 Building the automation project with Maven..."
    // Clean and compile the project
    sh 'mvn clean compile'
}

def testApp() {
    echo "🧪 Running TestNG tests..."
    // Run tests and generate reports
    sh 'mvn test'
}

def deployApp() {
    echo "📦 Archiving reports and logs..."

    // Publish TestNG results (Surefire plugin)
    junit '**/target/surefire-reports/*.xml'

    // Archive other artifacts (like logs, screenshots)
    archiveArtifacts artifacts: '**/target/**/*.log, **/target/screenshots/**', fingerprint: true
}

return this
