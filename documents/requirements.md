# Requirements Document 
## 1 Introduction
The designed software is web app that represent an lightweight platform for creating and sharing presentations.
## 2 User Requirements

### 2.1 Software Interfaces

#### Used technologies
- HTML, CSS, Typescipt and Angular (4+) on the front-end
- Java, Kotlin and Spring Framework on the backend
- MySQL/PostgreSQL RDBMS to store projects
- Redis DB to store temp keys and hashes.

### 2.2 User Interfaces

User interface will provide facilities to create, edit, delete and share presentations. For more details see mockups.

### 2.3 User Characteristics
- Developers, team leads, project managers, HR, resource managers - people in IT sphere, who need to explaint thier ideas in slides.
- Default staff of institute and school. Engineers, laborants, administration, managment, teachers and also students can use it.
- People with a great ideas (e.g., startups) who want to broadcast it.
### 2.4 Assumptions and Dependencies
- Performance issues in old browsers.
- Easy extensibility and changeability of content.
## 3 System Requirements
To use this application, you need a device with Enternet access and web browser installed. Recommended browsers:

- Google Chrome
- Mozilla Firefox
- Safari
- Yandex

### 3.1 Functional Requirements
3.1.1 Navigation bar in site header with:

- Profile (form with a user editable information)
- Presentations (list of your presentations)
- New slides (wokrspace)
- Search (list of search results)

3.1.2 Workspace:

- Workspace grid at the center
- List of available blocks at the left side
- List of tools at the left side
- Slides thumbnails at the bottom
- Inline editing tools at the blocks

3.1.3 On the center side of each page must be located content according section.

3.1.4 Logo at the site header.

3.1.5 Footer with:
- About (text)
- Contacts (text)

3.1.6 Internalization of toolset and content in Russian and English. Implemented by icons on right side of top the navigation bar

3.1.7 Implemention of database storing information about users and thier projects.

3.1.8 Share links and socials at the projects list view.

### 3.2 Non-Functional Requirements
1) Secure HTTPS channel
2) Throughtput. Simultaneously at least 1000 users at a time
3) Responsive Design
#### 3.2.1 SOFTWARE QUALITY ATTRIBUTES
- Performance - this platform is intended to be lightweight and esay to use, so it should be fast to provide it. App loading can take a several time (about 2 seconds), but all subsequest actions should not take more than 100ms.

- Reliability - this web app must be deployed 24 hours per day, so user should be able to access it whenever he wants. Measures of time when user can't access web page must be less than 24 hours per month.

