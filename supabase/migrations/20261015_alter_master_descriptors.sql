-- Migration: Add template_version and vector_checksum to master_descriptors
-- Date: 2026-10-15

ALTER TABLE public.master_descriptors 
ADD COLUMN IF NOT EXISTS template_version integer DEFAULT 1,
ADD COLUMN IF NOT EXISTS vector_checksum text;

-- Add index on vector_checksum for fast delta comparison
CREATE INDEX IF NOT EXISTS idx_master_descriptors_checksum 
ON public.master_descriptors(vector_checksum);

-- Ensure updated_at column exists
ALTER TABLE public.master_descriptors 
ADD COLUMN IF NOT EXISTS updated_at timestamptz DEFAULT now();

-- Trigger to auto-update updated_at on modification
CREATE OR REPLACE FUNCTION update_master_descriptors_modtime()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_master_descriptors_modtime ON public.master_descriptors;
CREATE TRIGGER trigger_master_descriptors_modtime
BEFORE UPDATE ON public.master_descriptors
FOR EACH ROW
EXECUTE FUNCTION update_master_descriptors_modtime();

-- Konversi vektor yang tersimpan sebagai string JSON menjadi jsonb array (idempotent dan aman diulang)
UPDATE public.master_descriptors
SET descriptor_json = (descriptor_json #>> '{}')::jsonb
WHERE jsonb_typeof(descriptor_json) = 'string';

-- Kueri verifikasi (hasil yang benar: hanya 'array'):
-- SELECT jsonb_typeof(descriptor_json), count(*) FROM master_descriptors GROUP BY 1;

