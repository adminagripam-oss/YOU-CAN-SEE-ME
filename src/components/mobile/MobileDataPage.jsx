import React from 'react';

export default function MobileDataPage({
  title,
  titleExtra,
  actions,
  tabs,
  toolbar,
  chips,
  children,
  className = '',
}) {
  return (
    <div className={`mobile-data-page ${className}`}>
      <header className="mobile-data-header no-print">
        <div className="mobile-data-titlerow">
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px', minWidth: 0 }}>
            {title && <h1 className="mobile-data-title">{title}</h1>}
            {titleExtra}
          </div>
          {actions && <div className="mobile-data-actions">{actions}</div>}
        </div>

        {tabs && <div className="mobile-tabs-container">{tabs}</div>}

        {toolbar && <div className="mobile-data-toolbar">{toolbar}</div>}

        {chips && <div className="mobile-chip-row">{chips}</div>}
      </header>

      <div className="mobile-data-body">
        {children}
      </div>
    </div>
  );
}
