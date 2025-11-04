public class MethodOverridingPractice {
    public static void main(String[] args) {
        /*
         * PRAKTIK HANDS-ON: Method Overriding
         *
         * Instruksi: Lengkapi semua latihan untuk menguasai method overriding,
         * covariant return types, dan best practices.
         */

        // ===== BASIC METHOD OVERRIDING =====
        System.out.println("=== BASIC METHOD OVERRIDING ===");

        // Latihan 1: Override method toString()
        Mahasiswa m1 = new Mahasiswa("2024001", "Budi", "Informatika", 3.75);
        System.out.println(m1);

        // Latihan 2: Override method equals()
        Mahasiswa m2 = new Mahasiswa("2024001", "Budi", "Informatika", 3.75);
        System.out.println("m1.equals(m2): " + m1.equals(m2));
        System.out.println("m1 == m2: " + (m1 == m2));

        // ===== OVERRIDING WITH SUPER =====
        System.out.println("\n=== OVERRIDING WITH SUPER ===");

        // Latihan 3: Override dengan memanggil super
        Dosen d1 = new Dosen("198901001", "Dr. Sarah", "Informatika", "PBO", 10);
        d1.displayInfo();

        // Latihan 4: Chain of overriding
        DosenProfessor dp = new DosenProfessor("197712001", "Prof. Andi", "Informatika", "AI", 20, "PhD");
        dp.displayInfo();

        // ===== COVARIANT RETURN TYPES =====
        System.out.println("\n=== COVARIANT RETURN TYPES ===");

        Dosen d2 = new Dosen("198902002", "Dr. Rina", "Informatika", "OOP", 12);
        Dosen dClone = d2.clone(); // covariant return type
        System.out.println("Clone berhasil dengan type yang lebih spesifik");
        System.out.println("original != clone: " + (d2 != dClone));
        System.out.println("original.equals(clone): " + d2.equals(dClone));

        // ===== OVERRIDING RULES =====
        System.out.println("\n=== OVERRIDING RULES ===");

        System.out.println("Widening access modifier: ALLOWED");
        System.out.println("Narrowing access modifier: COMPILE ERROR");
        System.out.println("Same return type: VALID");
        System.out.println("Covariant return type: VALID");
        System.out.println("Unrelated return type: COMPILE ERROR");
        System.out.println("Cannot override final method: COMPILE ERROR");
    }
}

// =================== KELAS PENDUKUNG ===================

class Mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;
    private double ipk;

    public Mahasiswa(String nim, String nama, String jurusan, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Mahasiswa{nim='" + nim + "', nama='" + nama + "', jurusan='" + jurusan + "', ipk=" + ipk + "}";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mahasiswa)) return false;
        Mahasiswa other = (Mahasiswa) obj;
        return this.nim.equals(other.nim);
    }
}

class Pegawai {
    protected String nip;
    protected String nama;
    protected String jurusan;

    public Pegawai(String nip, String nama, String jurusan) {
        System.out.println("Pegawai constructor called");
        this.nip = nip;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    public void displayInfo() {
        System.out.println("=== Info Pegawai ===");
        System.out.println("NIP: " + nip);
        System.out.println("Nama: " + nama);
        System.out.println("Jurusan: " + jurusan);
    }

    public Pegawai clone() {
        return new Pegawai(this.nip, this.nama, this.jurusan);
    }

    public final void finalMethodExample() {
        System.out.println("Final method ini tidak bisa dioverride.");
    }
}

class Dosen extends Pegawai {
    protected String mataKuliah;
    protected int pengalamanMengajar;

    public Dosen(String nip, String nama, String jurusan, String mataKuliah, int pengalamanMengajar) {
        super(nip, nama, jurusan);
        System.out.println("Dosen constructor called");
        this.mataKuliah = mataKuliah;
        this.pengalamanMengajar = pengalamanMengajar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Mata Kuliah: " + mataKuliah);
        System.out.println("Pengalaman: " + pengalamanMengajar + " tahun");
    }

    // Covariant return type: parent return Pegawai, child return Dosen
    @Override
    public Dosen clone() {
        return new Dosen(this.nip, this.nama, this.jurusan, this.mataKuliah, this.pengalamanMengajar);
    }
}

class DosenProfessor extends Dosen {
    private String gelar;

    public DosenProfessor(String nip, String nama, String jurusan, String mataKuliah, int pengalamanMengajar, String gelar) {
        super(nip, nama, jurusan, mataKuliah, pengalamanMengajar);
        System.out.println("DosenProfessor constructor called");
        this.gelar = gelar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Gelar Akademik: " + gelar);
    }
}
