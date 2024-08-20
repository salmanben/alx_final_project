# ALIXA Construction Materials Platform

Welcome to ALIXA, a comprehensive platform designed for the seamless reservation of construction materials. ALIXA enables clients and suppliers in the construction industry to efficiently manage material reservations with real-time inventory updates and a user-friendly interface.

## Features

### Core Features
- **Construction Materials Reservation**: ALIXA allows users to easily find, select, and reserve a wide variety of construction materials.
- **Real-Time Inventory Updates**: Users can view and reserve materials with up-to-date inventory levels.
- **Intuitive User Interface**: The platform's design ensures a smooth user experience, making it easy for clients to navigate and complete reservations.
- **Powerful Search**: Easily search through a vast inventory of construction materials to quickly find what you need.
- **Notifications**: Receive updates on your reservation status via SMS and email.
- **File Storage**: Integration with AWS S3 for reliable and scalable storage of material and category images.

## Getting Started

To set up and run the ALIXA Construction Materials Platform on your local machine:

### Clone the Repository
```bash
git clone https://github.com/username/alixa-platform.git

### Setup Instructions

1. **Create Database**: Create a database named `alixa`.

2. **Configure Database Connection**: 
   - Open the `application.properties` file.
   - Add the database connection details:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/alixa
     spring.datasource.username=your_database_username
     spring.datasource.password=your_database_password
     ```

3. **Admin Access**:
   - Start the application.
   - Log in using the following credentials:
     - **Email**: `admin@gmail.com`
     - **Password**: `12345`
   - After logging in, navigate to the dashboard to configure AWS S3, sms  and email settings.
   - Update your profile.


## Video Recorder
Here is a brief demonstration of some features and usage of ShopNet Ecommerce Platform: [Video Recorder](https://drive.google.com/file/d/1UA9tGgSDFkRSw4hV5Il03rH9Tgq-YPpB/view?usp=sharing)

## Contributing
We welcome contributions from the community to improve Alixa Platform. If you have ideas for new features, bug fixes, or enhancements, please feel free to submit a pull request on GitHub.

## Support
For any questions, feedback, or support requests, please contact salmanbenomar250@gmail.com.
