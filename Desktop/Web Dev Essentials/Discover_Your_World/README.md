# 🌍 Discover Your World

An interactive web application that allows users to explore world geography through an interactive map and geolocation features.

## 📋 Project Description

Discover Your World is an educational web application that helps users learn about continents and their key geographical features. Users can interact with the world map by clicking on continents or use their device's geolocation to automatically discover information about their current location.

## ✨ Features

- **Interactive World Map**: Click on any continent to view detailed information
- **Geolocation Detection**: Automatically detect your continent and display relevant geographical data
- **Continent Information**: Learn about famous rivers, mountains, and landmarks for each continent
- **Responsive Design**: Works on desktop and mobile devices
- **Custom Favicon**: Professional branding with custom favicon

## 🗺️ Continents Covered

The application provides information about all six continents:
- **North America** - Mississippi River, Denali, Grand Canyon
- **South America** - Amazon River, Aconcagua, Machu Picchu
- **Europe** - Danube River, Mont Blanc, Eiffel Tower
- **Africa** - Nile River, Mount Kilimanjaro, Sphinx
- **Asia** - Yangtze River, Mount Everest, Great Wall of China
- **Australia and Oceania** - Murray River, Mount Kosciuszko, Sydney Opera House

## 🚀 How to Use

1. **Open the Application**
   - Open `Discover_Your_World.html` in your web browser
   - The page will display a world map with clickable regions

2. **Explore by Clicking**
   - Click on any continent on the map to see information about:
     - Major rivers
     - Highest mountains
     - Famous landmarks

3. **Use Geolocation**
   - Click the "Discover Location" button
   - Allow browser access to your location when prompted
   - The app will automatically determine your continent and display relevant information

## 📁 Project Structure

```
Discover_Your_World/
│
├── Discover_Your_World.html    # Main HTML file
├── script.js                   # JavaScript functionality
├── README.md                   # Project documentation
│
├── Favicons/                   # Favicon files
│   ├── favicon.ico
│   ├── favicon-16x16.png
│   ├── favicon-32x32.png
│   ├── apple-touch-icon.png
│   └── site.webmanifest
│
└── Assets/                     # Image assets
    └── continents.jpg          # World map image
```

## 🛠️ Technologies Used

- **HTML5**: Structure and image mapping
- **JavaScript (ES6)**: Interactive functionality and geolocation API
- **Geolocation API**: Browser-based location detection
- **Image Maps**: Clickable regions on the world map

## 🌐 Browser Compatibility

- Chrome (recommended)
- Firefox
- Safari
- Edge
- Opera

**Note**: Geolocation features require HTTPS or localhost for security reasons. Some browsers may not support geolocation on file:// protocol.

## 📝 How It Works

### Image Map
The world map uses HTML image map technology with `<area>` tags to define clickable regions for each continent. Each region has specific coordinates that match the continent boundaries.

### JavaScript Functionality
- **Event Listeners**: Detect clicks on map areas and button
- **Geolocation API**: Access device GPS coordinates
- **Coordinate Mapping**: Convert latitude/longitude to continent identification
- **Data Storage**: Object-based storage of continent information

## 🔧 Setup Instructions

1. Clone or download the project folder
2. Ensure all files are in the correct directory structure
3. Open `Discover_Your_World.html` in a web browser
4. For geolocation features, consider running on a local server or HTTPS

### Running on a Local Server (Optional)

If geolocation doesn't work with file:// protocol:

```bash
# Using Python 3
python -m http.server 8000

# Using Node.js (if you have http-server installed)
npx http-server

# Then open: http://localhost:8000
```

## 🎓 Learning Objectives

This project demonstrates:
- HTML5 semantic structure
- Image map implementation
- JavaScript DOM manipulation
- Browser geolocation API
- Event handling
- Object data structures
- Conditional logic

## 🐛 Troubleshooting

**Problem**: Favicon not showing
- **Solution**: Use relative paths (e.g., `Favicons/favicon.ico`) instead of absolute paths
- Clear browser cache and hard refresh (Ctrl+Shift+R)

**Problem**: Geolocation not working
- **Solution**: Ensure you're allowing location access in browser permissions
- Try running on localhost or HTTPS
- Check browser console for errors

**Problem**: Map areas not clickable
- **Solution**: Verify image path is correct in `src` attribute
- Check that all `<area>` tags have matching IDs in JavaScript

## 👨‍💻 Author

Created as a web development learning project

## 📄 License

This project is open source and available for educational purposes.

## 🔮 Future Enhancements

Potential improvements:
- Add more detailed information for each continent
- Include country-level information
- Add styling with CSS
- Implement responsive design improvements
- Add animations and transitions
- Include more geographical facts and quiz features
- Support multiple languages

---

**Enjoy exploring the world! 🌎🌍🌏**
