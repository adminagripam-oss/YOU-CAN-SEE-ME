import { useState } from 'react';
import { CalendarIcon } from 'lucide-react';
import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog';
import { Label } from '@/components/ui/label';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

const BULAN = [
  'Januari', 'Februari', 'Maret', 'April', 'Mei', 'Juni',
  'Juli', 'Agustus', 'September', 'Oktober', 'November', 'Desember',
];

const NOW = new Date().getFullYear();
const TAHUN = Array.from({ length: 7 }, (_, i) => NOW - 5 + i);

export function PeriodePicker({ value, onApply }) {
  const [open, setOpen] = useState(false);
  const [draft, setDraft] = useState(value);

  const handleOpenChange = (isOpen) => {
    setOpen(isOpen);
    if (isOpen) setDraft(value);
  };

  const handleApply = () => {
    onApply(draft);
    setOpen(false);
  };

  return (
    <Dialog open={open} onOpenChange={handleOpenChange}>
      <DialogTrigger asChild>
        <Button
          variant="outline"
          className="h-9 gap-2 whitespace-nowrap"
        >
          <CalendarIcon className="w-4 h-4 opacity-60" />
          {BULAN[value.month].slice(0, 3)} {value.year}
        </Button>
      </DialogTrigger>

      <DialogContent className="sm:max-w-[420px]">
        <DialogHeader>
          <DialogTitle>Pilih Periode Absensi</DialogTitle>
          <DialogDescription>
            Tentukan bulan dan tahun yang ingin dipantau.
          </DialogDescription>
        </DialogHeader>

        <div className="grid grid-cols-2 gap-4 py-2">
          <div className="grid gap-2">
            <Label>Bulan</Label>
            <Select
              value={String(draft.month)}
              onValueChange={(v) => setDraft((d) => ({ ...d, month: Number(v) }))}
            >
              <SelectTrigger>
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                {BULAN.map((b, i) => (
                  <SelectItem key={b} value={String(i)}>{b}</SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>

          <div className="grid gap-2">
            <Label>Tahun</Label>
            <Select
              value={String(draft.year)}
              onValueChange={(v) => setDraft((d) => ({ ...d, year: Number(v) }))}
            >
              <SelectTrigger>
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                {TAHUN.map((t) => (
                  <SelectItem key={t} value={String(t)}>{t}</SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>
        </div>

        <DialogFooter>
          <Button variant="outline" onClick={() => setOpen(false)}>
            Batal
          </Button>
          <Button
            className="bg-green-600 text-white hover:bg-green-700 dark:bg-green-500 dark:text-green-950 dark:hover:bg-green-400"
            onClick={handleApply}
          >
            Terapkan
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}