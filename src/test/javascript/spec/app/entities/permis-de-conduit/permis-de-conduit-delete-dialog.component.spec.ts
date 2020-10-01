/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { Permis_de_conduitDeleteDialogComponent } from 'app/entities/permis-de-conduit/permis-de-conduit-delete-dialog.component';
import { Permis_de_conduitService } from 'app/entities/permis-de-conduit/permis-de-conduit.service';

describe('Component Tests', () => {
    describe('Permis_de_conduit Management Delete Component', () => {
        let comp: Permis_de_conduitDeleteDialogComponent;
        let fixture: ComponentFixture<Permis_de_conduitDeleteDialogComponent>;
        let service: Permis_de_conduitService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Permis_de_conduitDeleteDialogComponent]
            })
                .overrideTemplate(Permis_de_conduitDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(Permis_de_conduitDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Permis_de_conduitService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
