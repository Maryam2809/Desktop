# Finance App
A personal finance tracking app with a GUI interface that runs seamlessly inside Docker. 
This guide helps macOS users get started.

## 📦 Prerequisites
Before you begin, make sure you have the following installed on your Mac:

### 1. Docker Desktop
   Download and install: https://www.docker.com/products/docker-desktop

### 2. XQuartz (X11 Server for macOS)
   Download: https://www.xquartz.org

    After installation:
    
    Reboot your machine or log out and log back in.
    
    Open XQuartz.

    Go to XQuartz > Preferences > Security
    
    ✅ Check: "Allow connections from network clients"

## Running the App

### Step 1: Clone the Repo
git clone https://github.com/yourusername/finance_app.git
cd finance_app

### Step 2: Allow Docker to connect to XQuartz
In your terminal run:
xhost + 127.0.0.1

### Step 3: Start the App
docker-compose up

### Stopping the App
To stop and clean up:
docker-compose down


