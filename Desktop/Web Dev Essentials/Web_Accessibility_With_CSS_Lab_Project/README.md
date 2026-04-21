# Web Accessibility with CSS Lab Project

## Project Overview
This project demonstrates how to improve website accessibility using HTML and CSS.
It focuses on practical, beginner-friendly techniques that help all users, including users who rely on keyboard navigation and screen readers.

## Scenario
You are building a company website and must ensure the site is usable for people with disabilities.
The page is designed with accessible navigation, clear links, a predictable layout, readable text, and screen reader support.

## Learning Goals
By completing this lab, students practice how to:
- Build clear and consistent navigation
- Make links easy to identify
- Design a simple page layout using Flexbox
- Improve readability with strong contrast and appropriate font size
- Add hidden helper text for screen reader users

## Files in This Project
- index.html: Page structure and semantic elements
- styles.css: Accessibility-focused visual styling

## Lab Requirements and How They Are Met

### 1) Clear Navigation
What was required:
- Navigation bar with links
- Clear spacing between items
- Hover feedback

What is implemented:
- Horizontal navigation menu using list items
- Visible spacing between menu items
- Hover style change for better interaction feedback

### 2) Identifiable Links
What was required:
- Distinct color and underline
- Hover and focus feedback

What is implemented:
- Sidebar links are underlined and use a distinct color
- Links change color on hover
- Focus styles are provided for keyboard users

### 3) Simple and Predictable Layout
What was required:
- Sidebar + main content layout
- Flexbox structure

What is implemented:
- Main container uses Flexbox
- Sidebar and main content are clearly separated
- Responsive behavior stacks sections on smaller screens

### 4) Readable Text
What was required:
- Body font size of 16px
- Body text color of #333
- High contrast background color of #f4f4f4

What is implemented:
- Base body font size set to 16px
- Body text color set to #333
- Header/footer and key sections use light backgrounds and dark text
- Additional large-text and high-contrast examples are included

### 5) Screen Reader Accessibility
What was required:
- Create screen-reader-text class
- Add visually hidden label in HTML
- Associate button label using aria-labelledby

What is implemented:
- A visually hidden screen reader text class is defined in CSS
- A hidden label is added for the menu button
- The menu button uses aria-labelledby to reference the hidden label

## How to Run
1. Open the project folder in VS Code.
2. Open index.html in a browser.
3. Interact with the navigation, sidebar links, and content.
4. Test keyboard focus using the Tab key.

## Accessibility Testing Checklist
Use this checklist before submission:
- Navigation is easy to scan and hover states are visible
- Links are distinguishable from plain text
- Page layout remains understandable on desktop and mobile widths
- Body text is readable at normal zoom
- Keyboard users can see focus indicators
- Screen reader label exists for the menu button

## Student Tips
- Avoid removing outlines globally; keyboard users need visible focus.
- Do not hide important text using display: none when it should be read by assistive tools.
- Keep labels and link text meaningful.
- Test with zoom (125% to 200%) to confirm readability.

## Suggested Next Improvements
- Add a skip-to-content link at the top of the page
- Add a visible focus style for all interactive controls
- Add reduced-motion support using prefers-reduced-motion
- Add dark mode with strong contrast checks

## Conclusion
This lab shows that accessibility can be improved with simple, practical CSS and semantic HTML choices.
The result is a cleaner and more inclusive user experience for everyone.
