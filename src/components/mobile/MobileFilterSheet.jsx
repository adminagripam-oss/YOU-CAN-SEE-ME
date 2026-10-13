import React from 'react';
import {
  Sheet,
  SheetContent,
  SheetHeader,
  SheetTitle,
  SheetFooter,
} from '@/components/ui/sheet';
import { Filter, RotateCcw, Check } from 'lucide-react';

export default function MobileFilterSheet({
  open,
  onOpenChange,
  title = 'Filter Data',
  children,
  onReset,
  activeCount = 0,
}) {
  return (
    <Sheet open={open} onOpenChange={onOpenChange}>
      <SheetContent
        side="bottom"
        className="mobile-filter-sheet-content"
        style={{
          maxHeight: '85dvh',
          display: 'flex',
          flexDirection: 'column',
          padding: '16px',
          borderTopLeftRadius: '16px',
          borderTopRightRadius: '16px',
          background: 'var(--bg-card)',
          borderTop: '1px solid var(--border-color)',
        }}
      >
        <SheetHeader style={{ padding: 0, marginBottom: '12px' }}>
          <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', paddingRight: '28px' }}>
            <SheetTitle style={{ fontSize: '1.05rem', fontWeight: 800, color: 'var(--text-main)', display: 'flex', alignItems: 'center', gap: '8px', margin: 0 }}>
              <Filter size={18} style={{ color: 'var(--accent-primary)' }} />
              <span>{title}</span>
              {activeCount > 0 && (
                <span className="mobile-filter-sheet-badge">
                  {activeCount}
                </span>
              )}
            </SheetTitle>
          </div>
        </SheetHeader>

        <div
          className="mobile-filter-sheet-body"
          style={{
            flex: 1,
            overflowY: 'auto',
            WebkitOverflowScrolling: 'touch',
            display: 'flex',
            flexDirection: 'column',
            gap: '12px',
            paddingRight: '2px',
          }}
        >
          {children}
        </div>

        <SheetFooter
          style={{
            display: 'flex',
            flexDirection: 'row',
            alignItems: 'center',
            gap: '10px',
            paddingTop: '12px',
            marginTop: '8px',
            borderTop: '1px solid var(--border-color)',
          }}
        >
          {onReset && (
            <button
              type="button"
              onClick={onReset}
              className="mobile-sheet-btn-secondary"
              style={{
                flex: 1,
                minHeight: '44px',
                display: 'inline-flex',
                alignItems: 'center',
                justifyContent: 'center',
                gap: '6px',
                borderRadius: '8px',
                border: '1px solid var(--border-color)',
                background: 'var(--bg-secondary)',
                color: 'var(--text-main)',
                fontSize: '0.85rem',
                fontWeight: 700,
                cursor: 'pointer',
              }}
            >
              <RotateCcw size={15} />
              <span>Reset</span>
            </button>
          )}

          <button
            type="button"
            onClick={() => onOpenChange(false)}
            className="mobile-sheet-btn-primary"
            style={{
              flex: 2,
              minHeight: '44px',
              display: 'inline-flex',
              alignItems: 'center',
              justifyContent: 'center',
              gap: '6px',
              borderRadius: '8px',
              border: 'none',
              background: 'var(--accent-primary)',
              color: '#ffffff',
              fontSize: '0.85rem',
              fontWeight: 700,
              cursor: 'pointer',
            }}
          >
            <Check size={16} />
            <span>Terapkan / Selesai</span>
          </button>
        </SheetFooter>
      </SheetContent>
    </Sheet>
  );
}
