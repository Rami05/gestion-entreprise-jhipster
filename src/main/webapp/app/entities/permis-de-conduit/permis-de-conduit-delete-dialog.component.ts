import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';
import { Permis_de_conduitService } from './permis-de-conduit.service';

@Component({
    selector: 'jhi-permis-de-conduit-delete-dialog',
    templateUrl: './permis-de-conduit-delete-dialog.component.html'
})
export class Permis_de_conduitDeleteDialogComponent {
    permis_de_conduit: IPermis_de_conduit;

    constructor(
        protected permis_de_conduitService: Permis_de_conduitService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.permis_de_conduitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'permis_de_conduitListModification',
                content: 'Deleted an permis_de_conduit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-permis-de-conduit-delete-popup',
    template: ''
})
export class Permis_de_conduitDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ permis_de_conduit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(Permis_de_conduitDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.permis_de_conduit = permis_de_conduit;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/permis-de-conduit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/permis-de-conduit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
