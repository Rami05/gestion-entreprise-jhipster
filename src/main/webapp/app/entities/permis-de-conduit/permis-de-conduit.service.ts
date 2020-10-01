import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPermis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

type EntityResponseType = HttpResponse<IPermis_de_conduit>;
type EntityArrayResponseType = HttpResponse<IPermis_de_conduit[]>;

@Injectable({ providedIn: 'root' })
export class Permis_de_conduitService {
    public resourceUrl = SERVER_API_URL + 'api/permis-de-conduits';

    constructor(protected http: HttpClient) {}

    create(permis_de_conduit: IPermis_de_conduit): Observable<EntityResponseType> {
        return this.http.post<IPermis_de_conduit>(this.resourceUrl, permis_de_conduit, { observe: 'response' });
    }

    update(permis_de_conduit: IPermis_de_conduit): Observable<EntityResponseType> {
        return this.http.put<IPermis_de_conduit>(this.resourceUrl, permis_de_conduit, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPermis_de_conduit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPermis_de_conduit[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
