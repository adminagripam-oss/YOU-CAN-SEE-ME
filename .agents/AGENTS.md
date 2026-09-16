

- **UI Native Select Custom Styling**: Wrap <select> in div.custom-select-wrapper with relative positioning. Apply ppearance: none; to hide OS dropdown arrow. Use absolute positioned lucide-react ChevronDown on the right. Map to Vanilla CSS variables (e.g. var(--bg-input), var(--bg-card) for options, var(--text-main)) to fully support dark mode.
- **UI Data Sorting Standard**: Whenever displaying a list of entities (such as Employees) in a Dropdown (<select>) or a Data Table, ALWAYS sort the array alphabetically (A-Z) by their primary display name before rendering.

- **Mobile-Friendly UI Standards**:
  1. **Dropdowns:** Prioritize clean strings (e.g., [Name] ([Last 4 chars of ID])) over long concatenations to prevent mobile UI overflow.
  2. **Data Tables:** Do NOT use card-morphing responsive tables unless requested. Instead, use .compact-mobile-table CSS class which drastically reduces ont-size and padding on mobile screens to fit more columns without horizontal scrolling.
