package hello.core.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;    // 추상인터페이스만 의존
                                                        // AppConfig 외부파일에서 구현 클래스 설정

    public MemberServiceImpl(MemberRepository memberRepository) {
        // 생성자를 통해 어떤 구현 객체가 들어올지(주입될지) 알 수 없음 -> 외부(AppConfig)에서 결정
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        // 의존관계에 대한 고민은 외부에 맡기고 '실행에만 집중'
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
